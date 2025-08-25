package com.blogwebsite.training_blog_website.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.blogwebsite.training_blog_website.dtos.AuthResponseDto;
import com.blogwebsite.training_blog_website.dtos.LoginRequest;
import com.blogwebsite.training_blog_website.dtos.SignUpRequest;

import jakarta.validation.Valid;

public interface AuthService {
	AuthResponseDto signUpRequest(SignUpRequest request);
	AuthResponseDto loginRequest(LoginRequest request);
}
