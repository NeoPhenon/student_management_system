package com.student_management_system.model;

import lombok.Getter;
import lombok.Setter;


public class Student {
	@Getter
	@Setter
	private int student_id;
	@Getter
	@Setter
	private String student_name;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String department;
	
	public Student() {}
	public Student(String student_name, String email, String department) {
		this.student_name = student_name;
		this.email = email;
		this.department = department;
	}
	
}
