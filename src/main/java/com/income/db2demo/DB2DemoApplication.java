package com.income.db2demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableCaching
public class DB2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DB2DemoApplication.class, args);
	}

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger("application");
	}
}
