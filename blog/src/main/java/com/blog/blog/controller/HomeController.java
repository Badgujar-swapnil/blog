package com.blog.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.blog.entities.User;
import com.blog.blog.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String home()
	
	{
		System.out.println("home");
		return "home";
	}

	@RequestMapping("/login")
	public String login()
	
	{
		System.out.println("login");
		return "login";
	}
	
@GetMapping("/register")
public String register()

{
	return "register";
}
@PostMapping("/createUser")
public String registerUser(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String about,

                           
                           @RequestParam String confirmPassword,
                           RedirectAttributes redirectAttributes) {

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setAbout(about);

    boolean registrationSuccessful = userService.registerUser(user, confirmPassword);
    System.out.println(" hi");

   if (!registrationSuccessful) {
        redirectAttributes.addFlashAttribute("error", "Registration failed. Check your details and try again.");
       return "redirect:/register";
   }

   return "redirect:/login"; // Redirect to login page after successful registration
}



}





	
	
	
	
	
	
	
	


