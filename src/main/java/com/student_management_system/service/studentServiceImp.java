package com.student_management_system.service;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.stereotype.Service;

import com.student_management_system.model.Student;


@Service
public class studentServiceImp implements studentService {

	private static final List<Student> STUDET_LIST = new ArrayList<>();
	
	private static final Map<Integer, Student> STUDENT_MAP = new HashMap<>();
	
	public static int next_id = 1;
	
	@Override
	public List<Student> listOfStudents(Student student){
			STUDET_LIST.add(student);
			STUDENT_MAP.put( student.getStudent_id() , student);
			student.setStudent_id(next_id);
			next_id++;
			return STUDET_LIST;
	}
	@Override
	public Student saveStudents(Student student) {
		if( !STUDENT_MAP.containsKey(student.getStudent_id())){
			STUDET_LIST.add(student);
			STUDENT_MAP.put( student.getStudent_id() , student);
			student.setStudent_id(next_id);
			
		}
		return student;
	}

	@Override
	public Student findStudentById(int id) {
		return STUDET_LIST.stream().filter( student -> student.getStudent_id() == id ).findFirst().orElse(null);
		// return STUDENT_MAP.getOrDefault(student.getStudent_id(), null );
	}

	@Override
	public void deleteStudent(int id) {
	Student student = findStudentById(id);
	
	if( student != null ) {
		STUDET_LIST.remove(student);
		STUDENT_MAP.remove(student.getStudent_id());
		}

	}

	@Override
	public void updateStudent( Student updatedStudent , int id ) {
		
		Student currentStudent = findStudentById(id);
			currentStudent.setStudent_name(updatedStudent.getStudent_name());
			currentStudent.setEmail(updatedStudent.getEmail());
			currentStudent.setDepartment(updatedStudent.getDepartment());
			
			int index = STUDET_LIST.indexOf(currentStudent);
			
			if( index != -1 ) {
				STUDET_LIST.set(index, currentStudent);
			}
			
			STUDENT_MAP.put(currentStudent.getStudent_id(), currentStudent);
	}
}