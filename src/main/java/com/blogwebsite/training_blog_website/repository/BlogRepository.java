package com.blogwebsite.training_blog_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogwebsite.training_blog_website.entity.BlogModel;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel,Long> {

}
