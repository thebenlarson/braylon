/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.controllers;

import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.service.SalesPerformanceService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author benth
 */
@Controller
@RequestMapping("/salesPerformance")
public class SalesPerformanceController {
    
    @Autowired
    SalesPerformanceService salesPerformanceService;
    
    @GetMapping("/view")
    public String dashboard(HttpServletRequest request, Model model) {
        System.out.println("Hello");
        List<User> list = salesPerformanceService.getUserListForLoggedInUser();
        if (list.isEmpty()){
            //list is empty, will want to redirect
        }

        model.addAttribute("salesPerformances", salesPerformanceService.getSalesPerformancesForUsers(list));
        //get files for this user
        return "salesPerformance/dashboard";
    }
}
