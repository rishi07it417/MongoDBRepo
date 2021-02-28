package com.test.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.mongo.model.Student;
import com.test.mongo.service.StudentService;

@RestController
public class StudentContoller {
	@Autowired
	private StudentService studentservice;
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentservice.getStudents();
	}
	
	@PutMapping("/students")
	public Long updateStudent(@RequestBody Student s) {
		return studentservice.updateStudent(s);
	}
	
	@GetMapping("/students/{id}")
	public Optional<Student> getStudentById(@PathVariable int id) {
		return studentservice.getStudentById(id);
	}
	
	@PostMapping("/students")
	public String insertStudent(@RequestBody Student s) {
		return studentservice.insertStudent(s);

	}
	
	@DeleteMapping("/students/{id}")
	public Long removStudentById(@PathVariable int id) {
		return studentservice.removStudentById(id);
	}
}
