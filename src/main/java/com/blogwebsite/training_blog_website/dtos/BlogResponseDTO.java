package com.blogwebsite.training_blog_website.dtos;


import java.time.LocalDateTime;

import com.blogwebsite.training_blog_website.entity.BlogModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponseDTO {
	private Long id;
	private String blog_content;	
	private String author_name;
	private LocalDateTime blog_createdAt;
	/*
	public BlogResponseDTO() {}
	public BlogResponseDTO(Long id, String blog_content, String author_name, LocalDateTime blog_createdAt) {
	    this.id = id;
	    this.blog_content = blog_content;
	    this.author_name = author_name;
	    this.blog_createdAt = blog_createdAt;
	}

	
    // --- Getters ---
	public Long getId() {
		return id;
	}
	
    public String getContent() {
        return blog_content;
    }

    public String getAuthor() {
        return author_name;
    }

    public LocalDateTime getCreatedAt() {
        return blog_createdAt;
    }

    // --- Setters ---
	public void setId(Long id) {
		this.id=id;
	}

	
    public void setContent(String content) {
        this.blog_content = content;
    }

    public void setAuthor(String author) {
        this.author_name = author;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.blog_createdAt = createdAt;
    }
    
    */
}
