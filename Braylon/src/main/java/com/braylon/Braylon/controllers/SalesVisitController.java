/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.controllers;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.entities.SalesVisit;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.entities.VisitPurpose;
import com.braylon.Braylon.entities.VisitTime;
import com.braylon.Braylon.service.SalesVisitService;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DEV10
 */
@Controller
public class SalesVisitController {
    
    private final SalesVisitService salesVisitservice;
   
    @Autowired
    public SalesVisitController(SalesVisitService salesVisitservice) {
        this.salesVisitservice = salesVisitservice;
    }
    
    //Employee View
    
    //All Visits for indicated Employee and display Sales Visit page with New Visit form
    @GetMapping("salesVisits")
    public String displayEmployeeSalesVisit(@AuthenticationPrincipal UserDetails user, SalesVisit salesvisit, Model model){
//        
         int id = Integer.parseInt(user.getUsername());
         model.addAttribute("employeeSalesVisits", salesVisitservice.findSalesVisitsByEmployeeId((id)));
         model.addAttribute("customers", salesVisitservice.findAllCustomers());
         model.addAttribute("visitPurposes", salesVisitservice.findAllVisitPurpose());
         model.addAttribute("visitTimes", salesVisitservice.findAllVisitTime());
         model.addAttribute("employee", salesVisitservice.findUserById((id)));
         return "salesVisits/salesVisits";
    }
    
    //Add new visit
    @PostMapping("newVisit")
    public String addNewVisit(@AuthenticationPrincipal UserDetails user, SalesVisit salesVisit, HttpServletRequest request, Model model){
       
       int id = Integer.parseInt(user.getUsername()); 
       int employeeId = Integer.parseInt(request.getParameter("employee"));
       int customerId = Integer.parseInt(request.getParameter("customer"));
       int purposeId = Integer.parseInt(request.getParameter("purpose"));
       int timeId = Integer.parseInt(request.getParameter("visitTime"));
       
       String visitDate = request.getParameter("visitDate");
       
       
       User employee = salesVisitservice.findUserById(employeeId);
       Customer customer = salesVisitservice.findCustomerById(customerId);
       VisitPurpose visitPurpose = salesVisitservice.findVisitPurpose(purposeId);
       VisitTime visitTime = salesVisitservice.findVisitTime(timeId);
       
       salesVisit.setUser(employee);
       salesVisit.setCustomer(customer);
       salesVisit.setPurpose(visitPurpose);
       salesVisit.setTime(visitTime);
       salesVisit.setVisit_date(LocalDate.parse(visitDate));
       
       salesVisitservice.addSalesVisit(salesVisit);
       
        return "redirect:/salesVisits";
    }

    //Display edit visit form
    @GetMapping("editVisit")
    public String displayEditSalesVisits(@AuthenticationPrincipal UserDetails user, SalesVisit salesVisit, String id, Model model){
         int userid = Integer.parseInt(user.getUsername());
         model.addAttribute("salesVisit", salesVisitservice.findSalesVisitById(Integer.parseInt(id)));
         model.addAttribute("employee", salesVisitservice.findUserById((userid)));
         model.addAttribute("customers", salesVisitservice.findAllCustomers());
         model.addAttribute("visitPurposes", salesVisitservice.findAllVisitPurpose());
         model.addAttribute("visitTimes", salesVisitservice.findAllVisitTime());
         return "editVisit/editVisit";
    }
    
    //Updates a sales visit
    @PostMapping("editVisit")
    public String editSalesVisit(@AuthenticationPrincipal UserDetails user, SalesVisit salesVisit, String id, HttpServletRequest request, Model model){
       
       int userid = Integer.parseInt(user.getUsername());
       
       model.addAttribute("employee", salesVisitservice.findUserById((userid)));
       
       int salesVisitId = Integer.parseInt(request.getParameter("visit_id"));
       int employeeId = Integer.parseInt(request.getParameter("employee"));
       int customerId = Integer.parseInt(request.getParameter("customer"));
       int purposeId = Integer.parseInt(request.getParameter("purpose"));
       int timeId = Integer.parseInt(request.getParameter("visitTime"));
       
       String visitDate = request.getParameter("visitDate");
       
       
       SalesVisit thisSalesVisit = salesVisitservice.findSalesVisitById(salesVisitId);
       User employee = salesVisitservice.findUserById(employeeId);
       Customer customer = salesVisitservice.findCustomerById(customerId);
       VisitPurpose visitPurpose = salesVisitservice.findVisitPurpose(purposeId);
       VisitTime visitTime = salesVisitservice.findVisitTime(timeId);
       
       salesVisit.setVisit_id(thisSalesVisit.getVisit_id());
       salesVisit.setUser(employee);
       salesVisit.setCustomer(customer);
       salesVisit.setPurpose(visitPurpose);
       salesVisit.setTime(visitTime);
       salesVisit.setVisit_date(LocalDate.parse(visitDate));
      
       salesVisitservice.editSalesVisit(salesVisit);
       
        return "redirect:/salesVisits";
    }
    
    @GetMapping("deleteVisit")
    public String deleteVisit(@AuthenticationPrincipal UserDetails user, Integer id, HttpServletRequest request, Model model){
        
        int userid = Integer.parseInt(user.getUsername());
       
        model.addAttribute("employee", salesVisitservice.findUserById((userid)));
        
        SalesVisit salesvisit = salesVisitservice.findSalesVisitById((id));
        String salesVisitId = request.getParameter("visit");
        User employee = salesvisit.getUser();
        int employeeId = employee.getEmployee_id();
        
        salesVisitservice.deleteSalesVisitByID(id); 
        
        return "redirect:/salesVisits";
    }
    
}