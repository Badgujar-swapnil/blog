package com.blog.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;
import com.blog.blog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
//    @Autowired
//    public UserService(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    public boolean registerUser(User user, String confirmPassword) {
        if (userRepository.findByEmail(user.getEmail()) != null )
            {
            return false; // User already exists
        }

        if (!user.getPassword().equals(confirmPassword)) {
            return false; // Passwords do not match
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true; // Registration successful
    }

//    public User getCurrentAuthenticatedUser() {
//        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//        return userRepository.findByEmail(username); // Assuming your user has email as a unique identifier
//    }
    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof DefaultOidcUser) {
            DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
            // Assuming you have a User entity class that you're returning:
            User user = new User(); // Or map the details from oidcUser to your User class
            user.setEmail(oidcUser.getEmail()); // Example of mapping data
            return user;
        } else if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Assuming your UserService has a method to find a User by username:
            return userRepository.findByEmail(userDetails.getUsername());
        }
        return null;
    }

   

}
