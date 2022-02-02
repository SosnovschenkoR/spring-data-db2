package com.income.db2demo.controller;

import com.income.db2demo.dto.ProductDto;
import com.income.db2demo.entity.Product;
import com.income.db2demo.mapper.MapStructMapper;
import com.income.db2demo.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class ProductController {

    private final ProductService productService;
    private final MapStructMapper mapStructMapper;

    @Autowired
    private Logger logger;

    @Autowired
    public ProductController(ProductService productService, MapStructMapper mapStructMapper) {
        this.productService = productService;
        this.mapStructMapper = mapStructMapper;
    }

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/product/list";
    }

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {
        Product product = productService.getById(Long.valueOf(id));
        model.addAttribute("product", product);
        logger.info(String.valueOf(product));
        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Product product = productService.getById(Long.valueOf(id));
        ProductDto productDto = mapStructMapper.productToProductDTO(product);

        model.addAttribute("productDto", productDto);
        return "product/productdto";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {
        Product product = new Product();
        product.setPrice(new BigDecimal(1000));
        product.setDescription("Test product");
        product.setImageUrl("https://image/url");
        productService.saveOrUpdate(product);
        model.addAttribute("productDto", new ProductDto());
        return "product/productdto";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductDto productDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productdto";
        }

        Product savedProduct = productService.saveOrUpdateProductDto(productDto);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(Long.valueOf(id));
        return "redirect:/product/list";
    }
}
