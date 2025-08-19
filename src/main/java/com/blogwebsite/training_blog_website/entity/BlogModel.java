package com.blogwebsite.training_blog_website.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String author;
	
	private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters ---
//    public String getContent() {
//        return content;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public Long getId() {
//    	return id;
//    }
//
//    // --- Setters ---
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
}