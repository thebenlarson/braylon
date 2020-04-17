/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.Role;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.repositories.RoleRepo;
import com.braylon.Braylon.repositories.UserRepo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 *
 * @author ibarbie
 */
@Service
public class UserService {
  
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  
  public UserService(UserRepo userRepo,
                        RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
  }
  
  // METHODS FOR USER
  
  public User saveUser(User user){
        return userRepo.save(user);
    }
    
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
    
    public User findUserById(int id){
            return userRepo.findById(id).orElse(null);
    }
    
    public void deleteUserById(int id){
       userRepo.deleteById(id);
   }
    
     public User createOrUpdateUser(User entity) {
        if (entity.getUsername() == null) {
            entity = userRepo.save(entity);

            return entity;
        } else {
            Optional<User> user = userRepo.findById(entity.getEmployee_id());

            if (user.isPresent()) {
                User newEntity = user.get();
                newEntity.setEmployee_id(entity.getEmployee_id());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.setPassword(entity.getPassword());
                newEntity.setRoles(entity.getRoles());
                newEntity.setEnabled(entity.getEnabled());
               
                newEntity = userRepo.save(newEntity);

                return newEntity;
            } else {
                entity = userRepo.save(entity);

                return entity;
            }
        }
    }
     //Method not complete or tested
     public User editUser(User entity) {
        
            Optional<User> user = userRepo.findById(entity.getEmployee_id());

            if (!user.isPresent()) {
              return entity;
            }else {
                User newEntity = user.get();
                newEntity.setEmployee_id(entity.getEmployee_id());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.setPassword(entity.getPassword());
                newEntity.setRoles(entity.getRoles());
                newEntity.setEnabled(entity.getEnabled());
               
                newEntity = userRepo.save(newEntity);

                return newEntity;
            }
        }
    
    
  
    // METHODS FOR ROLE
    
    public Role saveRole(Role role){
        return roleRepo.save(role);
    }
    
    public Role findRoleById(int id){
        return roleRepo.findById(id).orElse(null);
    }
    
    /*public List<Role> findRoleByUser(int employeeId){
           return roleRepo.findByUserEmployeeId(employeeId); 
    }*/
    
    public void deleteRole(int id) {
        roleRepo.deleteById(id);
    }
    
    public void deleteAllRoles(List<Role> roles){
        roleRepo.deleteAll(roles);
    }
    
    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }
    
    
}
