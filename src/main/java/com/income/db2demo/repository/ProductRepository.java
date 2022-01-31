package com.income.db2demo.repository;

import com.income.db2demo.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    Optional<Product> findById(Long id);
    Product getById(Long id);
}

