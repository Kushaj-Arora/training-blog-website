package com.blogwebsite.training_blog_website.dtos;

import com.blogwebsite.training_blog_website.enums.BlogCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotNull(message="Please select category")
	private BlogCategory category;
}
