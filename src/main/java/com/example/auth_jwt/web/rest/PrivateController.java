package com.example.auth_jwt.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/private")
public class PrivateController {

    @GetMapping
    public String hello(){
        return "Hello World private";
    }
}
