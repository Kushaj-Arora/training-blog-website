package com.blogwebsite.training_blog_website.dtos;

import java.time.LocalDateTime;

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
	private Long blog_id;
	private String blog_content;	
	private String author_name;
	private LocalDateTime blog_createdAt;
}
