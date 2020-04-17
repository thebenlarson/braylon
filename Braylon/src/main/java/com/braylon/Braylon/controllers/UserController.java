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
import com.braylon.Braylon.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

  @Autowired
  UserService service;

  @GetMapping({"/", "/landingPage"})
  public String displayHomePage() {

    return "login/landingPage";
  }

  @GetMapping("/login")
  public String login() {

    List<Role> roleList = roles.findAll();
    System.out.println(roleList);

    User userList = users.findById(100000001).orElse(null);
    System.out.println(userList);

    return "login/loginPage";
  }

  // After redirect:/dashboard
  //--------------------------------------------------------------------- USER CONTROLLER METHODS
  
  @GetMapping("/admin")
  public String displayAdminPage(Model model) {
    model.addAttribute("users", users.findAll());
    return "admin";
  }

  @GetMapping("/addUser")
  public String addUser(User user, Model model) {

    model.addAttribute("roles", service.findAllRoles());

    return "addUser";
  }

  @PostMapping("/addUser")
  public String addUser(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {
    
    if (result.hasErrors()) {
            return "addUser";
        }

        users.save(user);
        return "redirect:list";
    }
  

  @GetMapping("/editUser")
  public String editUser(int id, Model model) {
    User user = service.findUserById(id);
    List<Role> roleList = (List<Role>) service.findUserById(id);

    model.addAttribute("user", user);
    model.addAttribute("roleList", service.findAllRoles());

    return "editUser";
  }
  
  /*@PostMapping("/editUser")
    public String updateUser(@Valid User user, BindingResult result, HttpServletRequest request) {
        Set<Role> roles = new HashSet<>();
        String roleList = request.getParameter("role_id"){
        Role role =  service.findRoleById(Integer.parseInt(roleList));
        //roleList.add(roles);
        user.set<Role>(role);
        
        
        if(result.hasErrors()) {
            return "editUser";
        }
        
        service.saveUser(user);
        
        return "redirect:/";
    }*/

  /**
   *
   * @param user
   * @param result
   * @param request
   * @param model
   * @return
   */

  
  @PostMapping("/editUser")
    public String updateUser (User user, BindingResult result, HttpServletRequest request, Model model) {
        
       String roleList = request.getParameter("role_id");
       var role = roles.getRoleByRole(roleList);
       user.setRoles((Set<Role>) role);
       
        if(result.hasErrors()) {
            model.addAttribute("roleList", service.findAllRoles());
            
            return "updateUser";
        }
       service.saveUser(user);
        
        
        return "redirect:/";
    }
  
  
  //---------------------------------------------------------------------------->ASHLEY'S UPDATE BELOW

  @PostMapping("/addUser")
  public String addUser(int employee_id, String password) {
    User user = new User();
    // Need to create a method to automatically assign employee id
    user.setEmployee_id(employee_id);
    // Create Password and Set in DB
    user.setPassword(password);
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

  @PostMapping(value = "/editUser")
  public String editUserAction(String[] roleIdList, Boolean enabled, Integer employee_id) {
    User user = users.findById(employee_id).orElse(null);

    if (enabled != null) {
      user.setEnabled(enabled);
    } else {
      user.setEnabled(false);
    }

    Set<Role> roleList = new HashSet<>();
    for (String roleId : roleIdList) {
      Role role = roles.getOne(Integer.parseInt(roleId)); //Hey Ashley this line was throwing errors, so I changes the method to getOne
      roleList.add(role);
    }
    user.setRoles(roleList);
    users.save(user);

    return "redirect:/admin";

  }

}
