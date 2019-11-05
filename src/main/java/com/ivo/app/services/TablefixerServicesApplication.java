package com.ivo.app.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
public class TablefixerServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablefixerServicesApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("Date in UTC: " + new Date().toString());
//		TimeZone.setDefault(TimeZone.getTimeZone("CST"));

//		System.out.println("Date in CST: " + new Date().toString());


	}
}
