package com.sandy.SpringSecEx.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "Welcome to spring boot security"+request.getSession().getId();
    }



}
