package com.blogwebsite.training_blog_website.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blogwebsite.training_blog_website.dtos.AuthResponseDto;
import com.blogwebsite.training_blog_website.dtos.LoginRequest;
import com.blogwebsite.training_blog_website.dtos.SignUpRequest;
import com.blogwebsite.training_blog_website.entity.UserModel;
import com.blogwebsite.training_blog_website.exceptions.UserAlreadyExistsException;
import com.blogwebsite.training_blog_website.repository.UserRepository;
import com.blogwebsite.training_blog_website.service.AuthService;

@Service
public class AuthserviceImpl implements AuthService{

	private final UserRepository userRepo;
	
	public AuthserviceImpl(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	@Override
	public AuthResponseDto signUpRequest(SignUpRequest request) {
		Optional<UserModel> userDataByUserName=userRepo.findByUsername(request.getUsername());
		Optional<UserModel> userDataByEmail=userRepo.findByEmail(request.getEmail());
		if(userDataByUserName.isPresent() || userDataByEmail.isPresent()) {
			throw new UserAlreadyExistsException("UserData Already Found");
		}
		UserModel user=UserModel.builder().username(request.getUsername()).email(request.getEmail()).password(request.getPassword()).build();
		UserModel savedUser=userRepo.save(user);
		return mapUserEntityToResponseDTO(savedUser, true);
	}
	
	@Override
	public AuthResponseDto loginRequest(LoginRequest request) {
		Optional<UserModel> userDataByUserName=userRepo.findByUsername(request.getUsername());
		if(userDataByUserName.isPresent()) {
			UserModel user=userDataByUserName.get();
			if(user.getPassword().equals(request.getPassword())) {
				return mapUserEntityToResponseDTO(user, true);
			}
			else {
				throw new UserAlreadyExistsException("Invalid Password");
			}
		}
		throw new UserAlreadyExistsException("Invalid Username/Password");
	}

	
	private AuthResponseDto mapUserEntityToResponseDTO(UserModel userModelResp,boolean isUserAuthenticated) {
		return AuthResponseDto.builder().isUserAuthenticated(isUserAuthenticated).username(
				userModelResp.getUsername()).build();
	}
}
