package com.test.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.mongo.dao.StudentInfo;
import com.test.mongo.dao.StudentInfoImpl;
import com.test.mongo.model.Student;

@Service
public class StudentService {
	
	@Autowired
	@Qualifier("StudentInfoImpl")
	private StudentInfo student;
	
	public List<Student> getStudents(){
		return student.getStudents();
	}
	public Long updateStudent(Student s) {
		return student.updateStudent(s);
	}
	public Optional<Student> getStudentById(int id) {
		return Optional.ofNullable(student.getStudentById(id).orElse(new Student()));
	}
	
	public String insertStudent(Student s) {
		return student.insertStudent(s);

	}
	public Long removStudentById(int id) {
			return student.removStudentById(id);
	}
}
