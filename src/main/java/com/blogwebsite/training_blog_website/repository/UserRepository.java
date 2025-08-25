package com.blogwebsite.training_blog_website.repository;

import com.blogwebsite.training_blog_website.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE u.username = :username")
    Optional<UserModel> findByUsername(String username);

    @Query("SELECT u FROM UserModel u WHERE u.email = :email")
    Optional<UserModel> findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM UserModel u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM UserModel u WHERE u.email = :email")
    boolean existsByEmail(String email);
}
