package com.app.aspect;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.controller.StudentController;
import com.app.jdbc.model.StudentAudit;
import com.app.model.StudentModel;
import com.app.repository.StudentAuditRepository;

@Aspect
@Component
public class StudentServiceAspect {

	static Logger logger = Logger.getLogger(StudentServiceAspect.class);
	
	@Before(value="execution(* com.app.service.StudentServiceImpl.*(..))")
	public void validateUser(JoinPoint joinPoint) {
		logger.info("beforeAdvice validateUser");
		logger.info("joinPoint method:"+ joinPoint.getSignature());
		logger.info("joinPoint arg:"+ joinPoint.getArgs());
		
		if(joinPoint.getArgs() !=null) {
			Object[] args = joinPoint.getArgs();
			for(Object arg:args) {
				logger.info("arg-->:"+arg.toString());
			}
		}	
	}
	
	
	@Autowired
	StudentAuditRepository studentAuditRepository;
	

	@AfterReturning(value="execution(* com.app.service.StudentServiceImpl.createStudent(..)) and args(studentModel, user)", returning = "sno")
	public void afteReturingOnCreate(JoinPoint joinPoint, StudentModel studentModel, String user, Long sno) {
		logger.info("afteReturingOnCreate invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
		logger.info("user:"+ user);
		logger.info("sno:" + sno);
		
		//DB Call
		/**
		 * User = userrepo.findByName(user);
		 * if(user.getRole() == "admin") {
		 * 	continue;
		 * }
		 * else {
		 * 	throw exception Forbidee("not authrozed");
		 * }
		 */
		
		
		StudentAudit studentAudit = new StudentAudit();
		studentAudit.setSno(sno);
		studentAudit.setSname(studentModel.getSname());
		studentAudit.setDate(new Date());
		studentAudit.setAccessed_by(user);
		
		studentAuditRepository.save(studentAudit);
		
	}
	
	
	@After(value="execution(* com.app.service.StudentServiceImpl.updateStudent(..)) and args(sno, studentModel, user)")
	public void afteReturingOnUpdate(JoinPoint joinPoint, int sno, StudentModel studentModel, String user) {
		logger.info("afteReturingOnUpdate invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
		logger.info("user:"+ user);
		logger.info("sno:" + sno);
		
		StudentAudit studentAudit = new StudentAudit();
		studentAudit.setSno(Long.valueOf(sno));
		studentAudit.setSname(studentModel.getSname());
		studentAudit.setDate(new Date());
		studentAudit.setAccessed_by(user);
		
		studentAuditRepository.save(studentAudit);
	}
	
	/*
	@Before(value="execution(* com.app.service.StudentServiceImpl.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("beforeAdvice invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info("joinArgs:"+ joinPoint.getArgs());
		if(joinPoint.getArgs() !=null) {
			Object[] args = joinPoint.getArgs();
			for(Object arg:args) {
				logger.info("arg:"+arg.toString());
			}
		}
	}
	
	
	@Before(value="execution(* com.app.service.StudentServiceImpl.createStudent(..)) and args(studentModel, user)")
	public void beforeAdviceOnCreate(JoinPoint joinPoint, StudentModel studentModel, String user) {
		logger.info("beforeAdviceOnCreate invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
	}
	
	@After(value="execution(* com.app.service.StudentServiceImpl.createStudent(..)) and args(studentModel, user)")
	public void afterAdviceOnCreate(JoinPoint joinPoint, StudentModel studentModel, String user) {
		logger.info("afterAdviceOnCreate invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
	}
	
	
	///Create
	@AfterReturning(value="execution(* com.app.service.StudentServiceImpl.createStudent(..)) and args(studentModel, user)", returning = "sno")
	public void afteReturingOnCreate(JoinPoint joinPoint, StudentModel studentModel, String user, Long sno) {
		logger.info("afteReturingOnCreate invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
		logger.info("user:"+ user);
		logger.info("sno:" + sno);
		
		StudentAudit studentAudit = new StudentAudit();
		studentAudit.setSno(sno);
		studentAudit.setSname(studentModel.getSname());
		studentAudit.setDate(new Date());
		studentAudit.setAccessed_by(user);
		
		studentAuditRepository.save(studentAudit);
	}
	
	//Update
	@After(value="execution(* com.app.service.StudentServiceImpl.updateStudent(..)) and args(sno, studentModel, user)")
	public void afteReturingOnUpdate(JoinPoint joinPoint, int sno, StudentModel studentModel, String user) {
		logger.info("afteReturingOnUpdate invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
		logger.info("user:"+ user);
		logger.info("sno:" + sno);
		
		StudentAudit studentAudit = new StudentAudit();
		studentAudit.setSno(Long.valueOf(sno));
		studentAudit.setSname(studentModel.getSname());
		studentAudit.setDate(new Date());
		studentAudit.setAccessed_by(user);
		
		studentAuditRepository.save(studentAudit);
	}
	
	@AfterReturning(value="execution(* com.app.service.StudentServiceImpl.getStudent(..))", returning = "studentModel")
	public void afterReturnOnGet(JoinPoint joinPoint, StudentModel studentModel) {
		logger.info("afterReturnOnGet invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(studentModel.getSname());
	}
	
	@AfterThrowing(value="execution(* com.app.service.StudentServiceImpl.throwExeption(..))", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		logger.info("afterThrowing invoked");
		logger.info("joinPoint:"+ joinPoint.getSignature());
		logger.info(ex.getMessage());
	}
	*/
}
