package com.income.db2demo.service;

import com.income.db2demo.dto.ProductDto;
import com.income.db2demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAll();

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductDto(ProductDto productForm);
}
