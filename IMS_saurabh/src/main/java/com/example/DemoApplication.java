package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//
//@EnableJpaRepositories(basePackages = "com.example.InventoryMaster")
//@EntityScan(basePackages = "com.example.InventoryMaster")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
