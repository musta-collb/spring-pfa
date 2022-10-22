package com.example.demo;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.example.demo.entities","com.example.demo.repositories", "com.example.demo.api","com.example.demo.security","com.example.demo.services", "com.example.demo.helpers"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
//		multipart.setMaxUploadSize(3 * 1024 * 1024);
//		return multipart;}
//	@Bean
//	@Order(0)
//	public MultipartFilter multipartFilter() { MultipartFilter multipartFilter = new MultipartFilter();
//		multipartFilter.setMultipartResolverBeanName("multipartResolver");
//		return multipartFilter;
//
//	}
}
