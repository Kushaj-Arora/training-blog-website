package com.blogwebsite.training_blog_website.service;

import java.util.List;

import com.blogwebsite.training_blog_website.dtos.BlogDTO;
import com.blogwebsite.training_blog_website.dtos.BlogResponseDTO;

import jakarta.validation.Valid;

public interface BlogService {
	BlogResponseDTO createNewBlog(BlogDTO request);
	List<BlogResponseDTO> getAllBlogs();
	BlogResponseDTO getBlogById(Long id);
	BlogResponseDTO updateBlog(Long id, BlogDTO updateReq);
	void deleteBlogByID(Long id);
}
