package com.blogwebsite.training_blog_website.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogwebsite.training_blog_website.dtos.AuthResponseDto;
import com.blogwebsite.training_blog_website.dtos.LoginRequest;
import com.blogwebsite.training_blog_website.dtos.SignUpRequest;
import com.blogwebsite.training_blog_website.service.AuthService;
import com.blogwebsite.training_blog_website.service.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final AuthService userService;
	public UserController(AuthService userService) {
		this.userService=userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponseDto> signUpRequest(@Valid @RequestBody SignUpRequest request){
		AuthResponseDto resp=userService.signUpRequest(request);
		return ResponseEntity.ok().header("X-Auth-Token",resp.getToken()).body(resp);
	}
	
	

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> loginRequest(@Valid @RequestBody LoginRequest request) {
		AuthResponseDto resp=userService.loginRequest(request);
		return ResponseEntity.ok().header("X-Auth-Token",resp.getToken()).body(resp);
	}
}