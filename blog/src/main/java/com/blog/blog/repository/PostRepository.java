package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByAuthor(User user);

}
