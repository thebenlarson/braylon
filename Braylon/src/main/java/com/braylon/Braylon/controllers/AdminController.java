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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ashleytulloch
 */
@Controller
public class AdminController {
    
    
    
    @Autowired
    UserRepo users;
    
    @Autowired
    RoleRepo roles;
    
   
    
    @GetMapping("/admin")
    public String displayAdminPage() {
        // Add in later but getting mapping done
        //model.addAttribute("users", users.findAll());
        return "admin/admin";
    }
    
    /*
    
    @PostMapping("/addUser")
    public String addUser(int employee_id, String password, String first_name, String last_name) {
        User user = new User();
        // Need to create a method to automatically assign employee id
        user.setEmployee_id(employee_id);
        // Create Password and Set in DB
        user.setPassword(password);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setEnabled(true);
        
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roles.getRoleByRole("ROLE_USER"));
        user.setRoles(userRoles);
        
        users.save(user);
        
        return "redirect:/admin";
    }
    
    @PostMapping("/deleteUser")
    public String deleteUser(Integer employee_id) {
        users.deleteById(employee_id);
        return "redirect:/admin";
    }
    
    @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer employee_id) {
        User user = users.findById(employee_id).orElse(null);
        List<Role> roleList = roles.findAll();
        
        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);
        
        return "editUser";
    }
    
    
    @PostMapping(value="/editUser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer employee_id, String first_name, String last_name) {
        User user = users.findById(employee_id).orElse(null);
        
        user.setFirstName(first_name);
        user.setLastName(last_name);
        
        if(enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }
        
        Set<Role> roleList = new HashSet<>();
        for(String roleId : roleIdList) {
            Role role = roles.getById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        user.setRoles(roleList);
        users.save(user);
        
        return "redirect:/admin";
        
    }

    */
}
