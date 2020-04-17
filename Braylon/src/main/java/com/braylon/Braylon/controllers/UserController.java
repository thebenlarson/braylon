/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.controllers;

import com.braylon.Braylon.entities.Role;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.repositories.RoleRepo;
import com.braylon.Braylon.repositories.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    
    // After redirect:/dashboard
    
}
