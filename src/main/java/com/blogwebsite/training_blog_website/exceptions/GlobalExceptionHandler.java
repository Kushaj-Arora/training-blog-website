package com.blogwebsite.training_blog_website.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.blogwebsite.training_blog_website.exceptions.BlogNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BlogNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleBlogNotFound(BlogNotFoundException ex) {
	    Map<String, String> error = new HashMap<>();
	    error.put("error", "Blog not found");
	    error.put("details", ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
	    Map<String, String> error = new HashMap<>();
	    error.put("error", "AUTHENTICATION_ERROR");
	    error.put("details", ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
