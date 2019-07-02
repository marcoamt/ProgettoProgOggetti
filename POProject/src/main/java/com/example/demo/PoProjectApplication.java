package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = { "product", "parseJSON"} )

@SpringBootApplication
public class PoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoProjectApplication.class, args);
	}

}
