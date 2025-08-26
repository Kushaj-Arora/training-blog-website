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

import com.blogwebsite.training_blog_website.dtos.BlogDTO;
import com.blogwebsite.training_blog_website.dtos.BlogResponseDTO;
import com.blogwebsite.training_blog_website.security.JwtUtils;
import com.blogwebsite.training_blog_website.service.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
	private final BlogService blogService;
	private final JwtUtils utils;
	public BlogController(BlogService blogService, JwtUtils utils) {
		this.blogService=blogService;
		this.utils=utils;
	}
	
	@PostMapping
	public ResponseEntity<BlogResponseDTO> createNewBlog(@Valid @RequestBody BlogDTO request){
		String authUser=utils.getAuthenticatedUsername();
		System.out.println("Auth User: "+authUser);
		return ResponseEntity.ok().body(blogService.createNewBlog(request));
	}
	
	@GetMapping
	public ResponseEntity<List<BlogResponseDTO>> getAllBlogs(){
		String authUser=utils.getAuthenticatedUsername();
		System.out.println("Auth User: "+authUser);
		return ResponseEntity.ok().body(blogService.getAllBlogs());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogResponseDTO> getBlogById(@PathVariable Long id){
		return ResponseEntity.ok(blogService.getBlogById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BlogResponseDTO> updateBlog(@PathVariable Long id, @RequestBody BlogDTO updateReq){
		return ResponseEntity.ok(blogService.updateBlog(id,updateReq));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBlogByID(@PathVariable Long id){
		blogService.deleteBlogByID(id);
		return ResponseEntity.noContent().build();
	}
	
}
