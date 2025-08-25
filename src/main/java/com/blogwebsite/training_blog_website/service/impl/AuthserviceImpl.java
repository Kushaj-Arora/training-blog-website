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
import com.blogwebsite.training_blog_website.utils.JwtUtils;

@Service
public class AuthserviceImpl implements AuthService{

	private final UserRepository userRepo;
	private final JwtUtils jwtUtil ;
	public AuthserviceImpl(UserRepository userRepo, JwtUtils jwtUtil) {
		this.userRepo=userRepo;
		this.jwtUtil=jwtUtil;
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
		String token=jwtUtil.generateToken(request.getUsername());
		return mapUserEntityToResponseDTO(savedUser, true,token);
	}
	
	@Override
	public AuthResponseDto loginRequest(LoginRequest request) {
		Optional<UserModel> userDataByUserName=userRepo.findByUsername(request.getUsername());
		if(userDataByUserName.isPresent()) {
			UserModel user=userDataByUserName.get();
			if(user.getPassword().equals(request.getPassword())) {
				String token=jwtUtil.generateToken(request.getUsername());
				return mapUserEntityToResponseDTO(user, true,token);
			}
			else {
				throw new UserAlreadyExistsException("Invalid Password");
			}
		}
		throw new UserAlreadyExistsException("Invalid Username/Password");
	}

	
	private AuthResponseDto mapUserEntityToResponseDTO(UserModel userModelResp,boolean isUserAuthenticated,String token) {
		return AuthResponseDto.builder().isUserAuthenticated(isUserAuthenticated).username(
				userModelResp.getUsername()).token(token).build();
	}
}
