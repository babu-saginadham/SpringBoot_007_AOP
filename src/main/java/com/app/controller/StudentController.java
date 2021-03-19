package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.StudentModel;
import com.app.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="student")
@Api(value="Student Management System", description="Operations pertaining to Student Management System")
public class StudentController {
	
	static Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping(value="/all")
	@ApiOperation(value = "View a list of available student", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<Object> getAll() {
		return ResponseEntity.ok(HttpStatus.OK).ok(studentService.getAllStudents());
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<StudentModel> getStudent2(@PathVariable("id") Integer id, 
			@PathVariable(value="startsWith", required = false) String nameStartsWith) {
		return ResponseEntity.ok(HttpStatus.OK).ok(studentService.getStudent(Long.valueOf(id)));
	}
	
	@GetMapping(value="/throw")
	public ResponseEntity<StudentModel> throwEx() throws Exception {
		return ResponseEntity.ok(HttpStatus.OK).ok(studentService.throwExeption());
	}
	
	
	@PostMapping()
	public ResponseEntity<Long> post(@RequestBody StudentModel student,
			@RequestHeader(name="user",required = false) String user) {
		Long sno = studentService.createStudent(student, user);
		return ResponseEntity.ok(HttpStatus.OK).ok(sno);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<String> put(@PathVariable("id") Integer id, @RequestBody StudentModel student,
			@RequestHeader(name="user",required = false) String user) {
		studentService.updateStudent(id, student, user);
		return null;
	}

	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id,
			@RequestHeader(name="user",required = false) String user) {
		studentService.deleteStudent(Long.valueOf(id), user);
		return null;
	}
	
	

}
