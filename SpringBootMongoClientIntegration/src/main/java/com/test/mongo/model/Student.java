package com.test.mongo.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document (collection = "student")
public class Student {
	@MongoId
	private int id;
	private String name;
	private String department;
	private List<StudentCourse> studentCourses;
	
	public Student(int id, String name, String department,List<StudentCourse> studentCourses ) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.studentCourses = studentCourses;
	}
	
	
	public Student() {
		super();
	}

	

	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}


	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", department=" + department + ", studentCourses="
				+ studentCourses + "]";
	}
	
	
	
}
