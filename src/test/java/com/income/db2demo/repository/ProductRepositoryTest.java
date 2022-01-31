package com.income.db2demo.repository;

import com.income.db2demo.DB2DemoApplication;
import com.income.db2demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class) //@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Transactional
//@ContextConfiguration(classes = {Db2DemoTestConfiguration.class})
class ProductRepositoryTest {

    @LocalServerPort //depends on annotation SpringBootTest
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenProductRepository_whenSaveAndRetreiveEntity_thenOK() {
        Product product = productRepository
                .save(new Product(1L, "test", new BigDecimal(100), "testUrl"));
        Product foundEntity = productRepository.getById(product.getId());

        assertNotNull(foundEntity);
        assertEquals(product.getDescription(), foundEntity.getDescription());
    }
}