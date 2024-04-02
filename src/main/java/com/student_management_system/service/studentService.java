package com.student_management_system.service;

import java.util.List;
import java.util.Optional;

import com.student_management_system.model.Student;

public interface  studentService {
	
	public abstract List<Student> listOfStudents(Student student);

	public abstract Student saveStudents(Student student);
	
	public abstract Student findStudentById(int id );
// let bang away with Optional this time to handle nullPointer exception and make program more overwelmingly daunting
	
	public abstract void deleteStudent(int id );
	
	public abstract void updateStudent( Student updatedStudent , int id );
}
