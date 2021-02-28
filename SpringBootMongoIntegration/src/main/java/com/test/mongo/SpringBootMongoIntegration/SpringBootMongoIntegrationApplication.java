package com.test.mongo.SpringBootMongoIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.test.mongo.model.Student;
import com.test.mongo.service.StudentService;

@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.test.mongo.dao")
@ComponentScan(basePackages ={"com.test.mongo.controller","com.test.mongo.service","com.test.mong.dao"})

@SpringBootApplication
public class SpringBootMongoIntegrationApplication {

	@Autowired
	private StudentService student;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoIntegrationApplication.class, args);
	}
	
	
}
