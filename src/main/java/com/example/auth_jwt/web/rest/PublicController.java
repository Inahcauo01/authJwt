package com.example.auth_jwt.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping
    public String hello(){
        return "Hello World public";
    }
}
