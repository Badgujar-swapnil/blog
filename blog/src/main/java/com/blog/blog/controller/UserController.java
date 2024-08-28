package com.blog.blog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.blog.Security.Helper;
import com.blog.blog.entities.Post;
import com.blog.blog.service.PostService;
import com.blog.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	private Helper helper;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
//	 @GetMapping("/user/dashboard")
//	    public String home() {
//	        return "dashboard"; 
//	    }
//	 @GetMapping("/user/userhome")
//	    public String userhome() {
//	        return "userhome"; // 
//	    }
	 @GetMapping("/user/post")
	    public String createPost() {
	        return "post"; 
	    }
	 @GetMapping("/user/profile")
	    public String profile() {
	        return "profile"; // Load content for profile page
	    }
	 @GetMapping("/user/logout")
	    public String logout() {
	        return "logout"; // Load content for profile page
	    }
	 @PostMapping("/user/submitpost")
	 public String createPosts( @RequestParam String title, @RequestParam String content )
	 
	 {
		 com.blog.blog.entities.User currentUser = userService.getCurrentAuthenticatedUser();
		 System.out.println("postsubmit");
		 Post post=new Post();
		 post.setContent(content);
		 post.setTitle(title);
		 post.setAuthor(currentUser);
		 postService.createPost(post);
		 return "redirect:/user/post"; 	 }
	 @GetMapping("/user/userhome")
	    public String getUserPosts(Model model) {
	        com.blog.blog.entities.User currentUser = userService.getCurrentAuthenticatedUser();

	        List<Post> posts = postService.getPostsByUser(currentUser);
              System.out.println(posts);
	        model.addAttribute("posts", posts);

	        return "/userhome"; 
	    }
	 @GetMapping("/user/post/{id}")
	 public String getPostDetails(@PathVariable("id") Integer id, Model model) {
	     Post post = postService.getPostById(id); 
	     model.addAttribute("post", post);
	     return "postdetail"; 
	 }
	
	 @GetMapping("/user/dashboard")
	    public String getAllPosts(Model model,  Authentication authentication ) {
		String username=helper.getEmailOfLoggedInUser(authentication);
		 System.out.println(username);
	        List<Post> posts = postService.getAllPosts();
	        model.addAttribute("posts", posts);
	        return "dashboard";
	    }

		 
	
	 
	

}
