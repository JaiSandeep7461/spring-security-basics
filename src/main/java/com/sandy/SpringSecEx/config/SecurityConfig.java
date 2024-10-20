package com.sandy.SpringSecEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        /*http.csrf(customizer-> customizer.disable());
        http.authorizeHttpRequests(request-> request.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults()); *//* If you didn't add this line you can't see the form*//*
        http.httpBasic(Customizer.withDefaults()); *//* for accessing post requests we are using it *//*
        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();*/
        http.csrf(customizer-> customizer.disable())
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("test1")
                .password("t@123")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("test2")
                .password("t@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }






}
