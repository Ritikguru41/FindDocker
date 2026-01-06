package com.lostphone.lostphone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lostphone.lostphone.entity.User;
import com.lostphone.lostphone.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/signup")
    public String registerPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {
        
         System.out.println("🔥 REGISTER HIT");
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));

        if ("POLICE".equals(role)) {
            user.setRole("ROLE_POLICE");
        } else {
            user.setRole("ROLE_FINDER");
        }

        userRepo.save(user);
        return "redirect:/login";
    }
}


