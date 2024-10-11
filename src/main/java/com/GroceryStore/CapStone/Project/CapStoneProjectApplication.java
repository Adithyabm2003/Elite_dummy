package com.GroceryStore.CapStone.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CapStoneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapStoneProjectApplication.class, args);
	}

}
