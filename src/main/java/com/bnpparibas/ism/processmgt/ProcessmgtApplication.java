package com.bnpparibas.ism.processmgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProcessmgtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {		SpringApplication.run(ProcessmgtApplication.class, args);

		System.out.println("Hello Process MGT");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("PUT", "DELETE","POST", "GET");
	}
}
