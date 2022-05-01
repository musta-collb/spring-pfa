package com.example.demo;

import com.example.demo.security.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.example.demo.entities","com.example.demo.repositories", "com.example.demo.api","com.example.demo.security"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
	@Bean
	public PasswordEncoder bCryptPasswordEncoder(){
		return new PasswordEncoder();
	}
}
