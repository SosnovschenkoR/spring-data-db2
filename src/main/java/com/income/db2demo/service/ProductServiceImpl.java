package com.income.db2demo.service;

import com.income.db2demo.dto.ProductDto;
import com.income.db2demo.entity.Product;
import com.income.db2demo.mapper.MapStructMapper;
import com.income.db2demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapStructMapper mapStructMapper;
    @Autowired
    private Logger logger;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MapStructMapper mapStructMapper) {
        this.productRepository = productRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    @Cacheable(value = "productCache", key = "#id", condition = "#id>1")
    public Product getById(Long id) {
        try {
            logger.info("Sleep for 2 seconds (emulate long query)");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductDto(ProductDto productDto) {
        return saveOrUpdate(mapStructMapper.productDTOToProduct(productDto));
    }
}
