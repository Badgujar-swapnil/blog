Blog Application
This is a Blog Application built using Spring Boot and Spring Security with both traditional form-based authentication and OAuth2 login (e.g., Google). The application allows users to register, log in, and create blog posts. Users can view their posts, and the application supports basic user profiles.

Features
User Registration and Authentication:
Users can register using their email and password.
The application supports OAuth2 login, specifically with Google.
Passwords are securely stored using BCryptPasswordEncoder.
Role-Based Access Control:

Only authenticated users can access certain features like creating and managing posts.
Blog Post Management:

Authenticated users can create, view, and manage their blog posts.
Posts are associated with the user who created them.
User Profile:

Each user has a profile section where they can view their details and manage their information.
OAuth2 Integration:

The application allows users to log in using their Google account.
The application distinguishes between form-based and OAuth2 logins to handle user data correctly.

Technologies Used
Backend:
Java, Spring Boot, Spring Security
Database:
MySQL
Frontend:
Thymeleaf, HTML, CSS, JavaScript
Security:
Spring Security with form login and OAuth2 (Google)


Open your browser and navigate to http://localhost:8080.
Usage
Home Page: Accessible to all users.
Registration: New users can register via /register.
Login: Users can log in using their email and password or via Google OAuth2.
Dashboard: Authenticated users can manage their posts and profile.
