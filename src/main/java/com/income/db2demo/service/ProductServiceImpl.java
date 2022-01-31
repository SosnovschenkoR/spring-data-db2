package com.income.db2demo.service;

import com.income.db2demo.dto.ProductDto;
import com.income.db2demo.entity.Product;
import com.income.db2demo.mapper.MapStructMapper;
import com.income.db2demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapStructMapper mapStructMapper;

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
    public Product getById(Long id) {
        return productRepository.getById(id);
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
