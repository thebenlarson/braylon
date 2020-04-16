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
    
    //Administration View

    //All Sales Visits for indicated employee
    @GetMapping("admin/employee")
    public String displayEmployeeSalesVisits( String id, Model model){
         model.addAttribute("employeeSalesVisits", salesVisitservice.findSalesVisitsByEmployeeId(Integer.parseInt(id)));
         model.addAttribute("salesVisits", salesVisitservice.findAllSalesVisit());
         model.addAttribute("customers", salesVisitservice.findAllCustomers());
         model.addAttribute("employees", salesVisitservice.findAllEmployees());
         return "admin";
    }
    
    //All Sales Visits for indicated customer
    @GetMapping("admin/customer")
    public String displayCustomerSalesVisits(String id, Model model){
         model.addAttribute("customerSalesVisits", salesVisitservice.findSalesVisitsByCustomerId(Integer.parseInt(id)));
         model.addAttribute("salesVisits", salesVisitservice.findAllSalesVisit());
         model.addAttribute("customers", salesVisitservice.findAllCustomers());
         model.addAttribute("employees", salesVisitservice.findAllEmployees());
         return "admin";
    }
    
    //Employee View
    
    //All Visits for indicated Employee and display Sales Visit page with New Visit form
    @GetMapping("salesVisits")
    public String displayEmployeeSalesVisit(String id, Model model){
         model.addAttribute("employeeSalesVisits", salesVisitservice.findSalesVisitsByEmployeeId(Integer.parseInt(id)));
         model.addAttribute("customers", salesVisitservice.findAllCustomers());
         model.addAttribute("visitPurposes", salesVisitservice.findAllVisitPurpose());
         model.addAttribute("visitTimes", salesVisitservice.findAllVisitTime());
         model.addAttribute("employee", salesVisitservice.findUserById(Integer.parseInt(id)));
         return "salesVisits";
    }
    
    //Add new visit
    @PostMapping("newVisit")
    public String addNewVisit(SalesVisit salesVisit, HttpServletRequest request, Model model){
        
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
       
      
        return "redirect:/salesVisits?="+employeeId;
    }

    //Display edit visit form
    @GetMapping("editVisit")
    public String displayEditSalesVisitsById(SalesVisit salesVisit, String id, Model model){
         model.addAttribute("salesvisit", salesVisitservice.findSalesVisitById(Integer.parseInt(id)));
         model.addAttribute("customers", salesVisitservice.findAllCustomers());
         model.addAttribute("visitPurposes", salesVisitservice.findAllVisitPurpose());
         return "editVisit";
    }
    
    //Updates a sales visit
    @PostMapping("editVisit")
    public String editSalesVisitById(SalesVisit salesVisit, String id, HttpServletRequest request, Model model){
       
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
      
        return "redirect:/salesVisit?id="+employeeId;
    }
    
    @GetMapping("deleteVisit")
    public String deleteSalesVisitById(HttpServletRequest request){
        String employeeId = request.getParameter("employee");
        String visitId = request.getParameter("visit_id");
        salesVisitservice.deleteSalesVisitByID(Integer.parseInt(visitId)); 
        return "redirect:/salesVisit?id="+employeeId;
    }
    
}



