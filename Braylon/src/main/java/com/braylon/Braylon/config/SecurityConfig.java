/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ashleytulloch
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsService userDetails;
    
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
    http    
                .authorizeRequests()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/", "/home", "/landingPage").permitAll()
                    .antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**", "/templates/**").permitAll()
                    .anyRequest().hasRole("USER")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?login_error=1")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll(); 
    
    
    }
    
}
