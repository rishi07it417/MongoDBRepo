package com.test.mongo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.mongo.model.Student;

public interface StudentInfo {
		
	public List<Student> getStudents();
	public Long updateStudent(Student s);
	public Optional<Student> getStudentById(int id);
	public String insertStudent(Student s);
	public Long removStudentById(int id);
}
