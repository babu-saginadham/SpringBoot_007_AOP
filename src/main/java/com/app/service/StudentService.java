package com.app.service;

import java.util.List;

import com.app.model.StudentModel;

public interface StudentService {

	public Long createStudent(StudentModel studentModel, String user);
	
	public void updateStudent(int sno, StudentModel studentModel, String user);
	
	public void deleteStudent(Long sno, String user);
	
	public List<StudentModel> getAllStudents();
	
	public StudentModel getStudent(Long sno);
	
	public StudentModel throwExeption() throws Exception;
	
}
