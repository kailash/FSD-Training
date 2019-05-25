package com.stackroute.muzixservice.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stackroute.muzixservice.exception.TrackAlreadyExistException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TrackAlreadyExistException.class)
	protected ResponseEntity<Object> handleEntityNotFound(TrackAlreadyExistException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Track Already Exist");
	}

	@ExceptionHandler(TrackNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(TrackNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Track with specified id is not found");
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errrr .... !!!! try after sometime");
	}

}
