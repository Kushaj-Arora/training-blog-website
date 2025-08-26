package com.blogwebsite.training_blog_website.service;

import java.util.List;

import com.blogwebsite.training_blog_website.dtos.BlogDTO;
import com.blogwebsite.training_blog_website.dtos.BlogResponseDTO;
import com.blogwebsite.training_blog_website.enums.BlogCategory;

import jakarta.validation.Valid;

public interface BlogService {
	BlogResponseDTO createNewBlog(BlogDTO request);
	List<BlogResponseDTO> getAllBlogs();
	List<BlogResponseDTO> getAllBlogsByUsername(String username);
	List<BlogResponseDTO> getAllBlogsByCategory(BlogCategory category);
	BlogResponseDTO getBlogById(Long id);
	BlogResponseDTO updateBlog(Long id, BlogDTO updateReq);
	void deleteBlogByID(Long id);
	
}
