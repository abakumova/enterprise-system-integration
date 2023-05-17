package com.esi.week10pre.controller;

import org.springframework.web.bind.annotation.*;

 

@RestController
@RequestMapping("/api")
public class SecController {

    @GetMapping("/public")
    public String publicAPI() {
        return "This is an unprotected endpoint";
    }

    @GetMapping("/admin")
    public String adminAPI() {
        return "Protected endpoint - only admins are allowed";
    }

    @GetMapping("/user")
    public String userAPI() {
        return "Protected endpoint - only users are allowed";
    }
}
