package com.test.mongo.model;

public class StudentCourse {

	private String courseName;
	private int courseFee;
	public StudentCourse(String courseName, int courseFee) {
		super();
		this.courseName = courseName;
		this.courseFee = courseFee;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	@Override
	public String toString() {
		return "StudentCourse [courseName=" + courseName + ", courseFee=" + courseFee + "]";
	}
	
	
}
