package guru.sfg.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
		
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
		e.getConstraintViolations().forEach(constraintViolation -> {
			errors.add(constraintViolation.toString());
		});

		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<String>> BindExceptionHandler(BindException e) {
		
		List<String> errors = new ArrayList<>(e.getAllErrors().size());
		e.getAllErrors().forEach(objectError -> {
			errors.add(objectError.getDefaultMessage());
		});

		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}

}
