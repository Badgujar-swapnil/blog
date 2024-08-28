package com.blog.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	public Post createPost(Post post) {
		
		
		post.setCreatedAt(LocalDateTime.now());
        return postRepo.save(post);
    }

	 public List<Post> getPostsByUser(User user) {
	        return postRepo.findByAuthor(user);
	    }
	 public Post getPostById(Integer id) {
		    return postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
		}
	 public List<Post> getAllPosts() {
	        return postRepo.findAll();
	    }
}
