package com.blogwebsite.training_blog_website.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {

	@NotBlank(message="Content can't be empty")
	private String blogContent;
	
	@NotBlank(message="Author name can't be empty")
	private String authorName;
}
