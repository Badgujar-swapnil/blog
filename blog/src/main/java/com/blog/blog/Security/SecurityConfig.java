package com.blog.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	 
	@Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        //http.authenticationProvider(authenticationProvider())
            http.csrf().disable().authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**").authenticated()
                .anyRequest().permitAll())
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/user/dashboard", true))
            .formLogin(login -> login
            		 .loginPage("/login")
            		.defaultSuccessUrl("/user/dashboard") 
                .usernameParameter("email")
                .permitAll())
            .logout(logout -> logout
            		.logoutUrl("/user/logout") 
                    .logoutSuccessUrl("/") 
                    .permitAll());
            return http.build();

        
    }

}