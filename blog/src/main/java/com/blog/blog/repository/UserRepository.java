package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);

	
   

	

}
