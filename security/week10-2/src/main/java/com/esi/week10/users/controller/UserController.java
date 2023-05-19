package com.esi.week10.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esi.week10.users.model.User;
import com.esi.week10.users.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user){
        return userService.addUser(user);
    }

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

    /*     
    @GetMapping("/authenticate")
    public List<String> authenticate() {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        List<String> roles = new ArrayList<>();
        if (principal instanceof MyUserDetails) {
            UserDetails details = (MyUserDetails) principal;
            for (GrantedAuthority authority: details.getAuthorities())
                roles.add(authority.getAuthority());
        }
        return roles;
    } 
    */
}
