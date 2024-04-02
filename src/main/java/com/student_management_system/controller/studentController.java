package com.student_management_system.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.student_management_system.model.Student;

import com.student_management_system.service.studentServiceImp;



@Controller
public class studentController {
	
	@Autowired
	private studentServiceImp serviceImp ;
	
	@RequestMapping(value = {"/students", "/"}, method = RequestMethod.GET)
	public ModelAndView getAllEmployees( @ModelAttribute Student student ){
		ModelAndView modelAndView = new ModelAndView();
		List<Student> listOfStudents = serviceImp.listOfStudents(student);
		modelAndView.addObject("studentDatas", listOfStudents);
		modelAndView.setViewName("students");
		return modelAndView;
	}
	
	@RequestMapping( value = "/students/createNew", method = RequestMethod.GET) // will only display form for create newStudent ,so we can create empty object for binding object field
	public ModelAndView createEmployees(){
		ModelAndView modelAndView = new ModelAndView();
		Student student = new Student();
		modelAndView.addObject("student",student);
		modelAndView.setViewName("createStudent");
		return modelAndView;
	}
	
	@RequestMapping( value = "/students/createNew" , method = RequestMethod.POST)
	public ModelAndView saveStudentDatas(@ModelAttribute("student")Student student ){
		 ModelAndView modelAndView = new ModelAndView();	
		serviceImp.saveStudents(student);
		 modelAndView.setViewName("redirect:/students");
		return modelAndView;
	}
	
	@RequestMapping( value = "/students/delete/{id}" , method = RequestMethod.GET)
	public ModelAndView deleteStudent( @PathVariable int id ) {
		 ModelAndView modelAndView = new ModelAndView();
		 serviceImp.deleteStudent(id);
		 modelAndView.addObject("studentDatas",serviceImp.listOfStudents(new Student()));
		 modelAndView.setViewName("students");
		 return modelAndView;
	}
	
	@RequestMapping( value = "/students/edit/{id}" , method = RequestMethod.GET)
	public ModelAndView editStudent(@PathVariable int id ) {
		
		 ModelAndView modelAndView = new ModelAndView();
		 
		Student targetedStudent =  serviceImp.findStudentById(id);
		
			modelAndView.addObject("student",targetedStudent);
			modelAndView.setViewName("editStudent");
		
		 return modelAndView;
	}
	@RequestMapping( value = "/students/update/{id}" , method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("student") Student updatedStudent , @PathVariable int id) {
		 ModelAndView modelAndView = new ModelAndView();
		 serviceImp.updateStudent(updatedStudent , id );
		 modelAndView.setViewName("redirect:/students");
		 return modelAndView;
	}
}
