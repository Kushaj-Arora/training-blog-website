package com.blogwebsite.training_blog_website.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.blogwebsite.training_blog_website.dtos.BlogDTO;
import com.blogwebsite.training_blog_website.dtos.BlogResponseDTO;
import com.blogwebsite.training_blog_website.entity.BlogModel;
import com.blogwebsite.training_blog_website.repository.BlogRepository;
import com.blogwebsite.training_blog_website.service.BlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService{
	private final BlogRepository blogRepo;
	public BlogServiceImpl(BlogRepository blogRepo) {
		this.blogRepo=blogRepo;
	}
	@Override
	public BlogResponseDTO createNewBlog(BlogDTO request) {
		BlogModel blog=BlogModel.builder().author(request.getAuthorName()).content(request.getBlogContent()).build();
		BlogModel savedBlog=blogRepo.save(blog);
		return mapEntityToResponseDTO(savedBlog);
	}
	
	
	public List<BlogResponseDTO> getAllBlogs(){
		return blogRepo.findAll().stream().map(this::mapEntityToResponseDTO).collect(Collectors.toList());
	}
	
	@Override
	public BlogResponseDTO getBlogById(Long id) {
		BlogModel existingBlog=blogRepo.findById(id).orElseThrow(()-> new RuntimeException("Blog Not Found"));
		return mapEntityToResponseDTO(existingBlog);
	}
	
	@Override
	public BlogResponseDTO updateBlog(Long id, BlogDTO updateReq) {
		BlogModel existingBlog=blogRepo.findById(id).orElseThrow(()-> new RuntimeException("Blog Not Found"));
		existingBlog.setAuthor(updateReq.getAuthorName());
		existingBlog.setContent(updateReq.getBlogContent());
		return mapEntityToResponseDTO(blogRepo.save(existingBlog));
	}

	@Override
	public void deleteBlogByID(Long id) {
		blogRepo.deleteById(id);
		return;
	}

	
	private BlogResponseDTO mapEntityToResponseDTO(BlogModel blogModelResp){
		return BlogResponseDTO.builder().id(
		        blogModelResp.getId()).blog_content(
		        blogModelResp.getContent()).author_name(
		        blogModelResp.getAuthor()).blog_createdAt(
		        blogModelResp.getCreatedAt()).build();
	}	
}
