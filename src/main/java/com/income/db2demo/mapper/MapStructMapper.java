package com.income.db2demo.mapper;

import com.income.db2demo.dto.ProductDto;
import com.income.db2demo.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    ProductDto productToProductDTO(Product product);
}
