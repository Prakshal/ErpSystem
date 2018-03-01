package com.brevitaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan(value = "com.brevitaz")
public class ErpSystemApplication
{

	public static void main(String[] args) {
		SpringApplication.run(ErpSystemApplication.class, args);
	}
}