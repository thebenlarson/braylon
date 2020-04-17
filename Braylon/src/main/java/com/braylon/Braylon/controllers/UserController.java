/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.controllers;

import com.braylon.Braylon.repositories.RoleRepo;
import com.braylon.Braylon.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ashleytulloch
 */
@Controller
public class UserController {
    
    
    @Autowired
    UserRepo users;
    
    @Autowired
    RoleRepo roles;
    
    
    @GetMapping({"/","/landingPage"})
    public String displayHomePage() {
        
        return "login/landingPage";
    }
    
    
    
    @GetMapping("/login")
    public String login() {
        
        return "login/loginPage";
    }
    
    @PostMapping("/login")
    public String loginRedirect(@AuthenticationPrincipal UserDetails user) {
        
        System.out.println(user.isEnabled());
        
        
        if (!user.isEnabled()) {
            return "redirect:/login/updatePassword";
        }
        
        else {
            return "/dashboard";
        }
    }
    
    // After redirect:/dashboard
    
}
