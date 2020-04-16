/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.Role;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.repositories.UserRepo;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashleytulloch
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserRepo users;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       User user = users.findUserByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("No User found for " + username + ".");
        }
        
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
