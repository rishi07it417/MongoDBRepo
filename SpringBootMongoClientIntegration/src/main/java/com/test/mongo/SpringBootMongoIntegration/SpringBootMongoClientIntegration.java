package com.test.mongo.SpringBootMongoIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.test.mongo.model.Student;
import com.test.mongo.service.StudentService;

@EnableAutoConfiguration
@ComponentScan(basePackages ={"com.test.mongo.controller","com.test.mongo.dao","com.test.mongo.service"})
@SpringBootApplication
public class SpringBootMongoClientIntegration {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoClientIntegration.class, args);
	}
	
	
}
