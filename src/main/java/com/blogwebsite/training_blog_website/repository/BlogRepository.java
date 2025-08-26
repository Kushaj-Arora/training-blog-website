package com.blogwebsite.training_blog_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogwebsite.training_blog_website.entity.BlogModel;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel,Long> {

    @Query("SELECT b FROM BlogModel b WHERE b.createdBy.username = :username")
    List<BlogModel> findByCreatedByUsername(@Param("username") String username);

}
