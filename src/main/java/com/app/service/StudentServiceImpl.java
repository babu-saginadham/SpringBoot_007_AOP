package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.controller.StudentController;
import com.app.jdbc.model.Student;
import com.app.model.StudentModel;
import com.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	static Logger logger = Logger.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Long createStudent(StudentModel studentModel, String user) {
		
		logger.info("Entered into createStudent()");
		
		Student studentToSave = new Student();
		studentToSave.setSname(studentModel.getSname());
		
		Student afterSave = studentRepository.save(studentToSave);
		
		logger.info("createStudent() DB operation success");
		
		return afterSave.getSno();
	}

	@Override
	public void updateStudent(int sno, StudentModel studentModel, String user) {
		
		Student student = studentRepository.findById(Long.valueOf(sno)).get();
		student.setSname(studentModel.getSname());
		
		studentRepository.save(student);
		
	}

	@Override
	public void deleteStudent(Long sno, String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StudentModel> getAllStudents() {
		List<Student> studentsFromDB = studentRepository.findAll();
		
		List<StudentModel> studentModels = new ArrayList<>();
		for(Student stud:studentsFromDB) {
			StudentModel studentModel = new StudentModel();
			studentModel.setSno(stud.getSno());
			studentModel.setSname(stud.getSname());
			
			studentModels.add(studentModel);
			
		}
		
		return studentModels;
	}

	@Override
	public StudentModel getStudent(Long sno) {
		// TODO Auto-generated method stub
		Student stud = studentRepository.findById(sno).get();
		
		
		/**
		 * invoke something
		 * req1 : are you expecting return ?
		 * 	res = invokeMethod();
		 * Req2 :
		 *    invokeMethod(stud);
		 *    
		 *  invokeASync(stud) --> calls and forgot
		 *  
		 *  res = asyClass.asyncMethodWithVoidReturnType1(); //File1
		 *  asyClass.asyncMethodWithVoidReturnType2(); //File2
		 *  asyClass.asyncMethodWithVoidReturnType3(); //File3
		 *  
		 *  
		 *  Main res = File1 + File 3 + file 2;
		 *  
		 *  
		 */
		//
		
		StudentModel studentModel = new StudentModel();
		
		//res
		studentModel.setSno(stud.getSno());
		studentModel.setSname(stud.getSname());
		
		
		//res
		
		return studentModel;
	}
	
	public StudentModel throwExeption() throws Exception {
		throw new Exception("Exception Occured:");
	}

}
