package com.sambit.example.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("deprecation")
@Controller
public class HomeController {

    private Map<String, LocalDateTime> usersLastAccess = new HashMap<>();
    
    @GetMapping("/registration")
    public String getCurrentUser(@AuthenticationPrincipal OidcUser user, Model model) {
        String email = user.getEmail();

        model.addAttribute("email", email);
        model.addAttribute("lastAccess", usersLastAccess.get(email));
        model.addAttribute("firstName", user.getGivenName());
        model.addAttribute("lastName", user.getFamilyName());

        usersLastAccess.put(email, LocalDateTime.now());

        return "home";
    }
    
    
}