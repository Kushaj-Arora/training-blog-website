package com.blogwebsite.training_blog_website.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.blogwebsite.training_blog_website.exceptions.BlogNotFoundException;
import com.blogwebsite.training_blog_website.dtos.BlogDTO;
import com.blogwebsite.training_blog_website.dtos.BlogResponseDTO;
import com.blogwebsite.training_blog_website.entity.BlogModel;
import com.blogwebsite.training_blog_website.repository.BlogRepository;
import com.blogwebsite.training_blog_website.service.BlogService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

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

		Optional<BlogModel> optionalBlog=blogRepo.findById(id);
		System.out.println("Existing Blog Data"+ optionalBlog);
		if (!optionalBlog.isPresent()){
			throw new BlogNotFoundException("Blog Not Found");
		}
		else{
			BlogModel existingBlog=optionalBlog.get();
			return mapEntityToResponseDTO(existingBlog);
		}
	}
	
	@Override
	public BlogResponseDTO updateBlog(Long id, BlogDTO updateReq) {
		Optional<BlogModel> optionalBlog=blogRepo.findById(id);
		System.out.println("Existing Blog Data"+ optionalBlog);
		if (optionalBlog.isPresent()){
			BlogModel existingBlog=optionalBlog.get();
			existingBlog.setAuthor(updateReq.getAuthorName());
			existingBlog.setContent(updateReq.getBlogContent());
			return mapEntityToResponseDTO(blogRepo.save(existingBlog));
		}
		else{
			throw new BlogNotFoundException("Blog Not Found");
		}
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
