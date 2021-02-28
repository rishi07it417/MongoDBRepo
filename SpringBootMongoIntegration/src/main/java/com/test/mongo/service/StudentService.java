package com.test.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mongo.dao.StudentInfo;
import com.test.mongo.model.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentInfo student;
	
	public List<Student> getStudents(){
		return student.findAll();
	}
	public Student updateStudent(Student s) {
		return student.save(s);
	}
	public Optional<Student> getStudentById(int id) {
		return Optional.ofNullable(student.findById(id).orElse(new Student()));
	}
	
	public Student insertStudent(Student s) {
		return student.insert(s);

	}
	public void removStudentById(int id) {
			student.deleteById(id);
	}
}
