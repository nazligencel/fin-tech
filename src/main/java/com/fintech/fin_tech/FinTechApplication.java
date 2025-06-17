package com.fintech.fin_tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FinTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinTechApplication.class, args);
	}

}
