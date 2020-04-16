/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.entities.Role;
import com.braylon.Braylon.entities.SalesVisit;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.entities.VisitPurpose;
import com.braylon.Braylon.entities.VisitTime;
import com.braylon.Braylon.repositories.CustomerOrderRepo;
import com.braylon.Braylon.repositories.CustomerRepo;
import com.braylon.Braylon.repositories.OrderStatusRepo;
import com.braylon.Braylon.repositories.ProductRepo;
import com.braylon.Braylon.repositories.RoleRepo;
import com.braylon.Braylon.repositories.SalesVisitRepo;
import com.braylon.Braylon.repositories.UserRepo;
import com.braylon.Braylon.repositories.VisitPurposeRepo;
import com.braylon.Braylon.repositories.VisitTimeRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEV10
 */
@Service
public class SalesVisitService {
    
    private final CustomerRepo customerRepo;
    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;
    private final ProductRepo productRepo;
    private final RoleRepo roleRepo;
    private final SalesVisitRepo salesVisitRepo;
    private final UserRepo userRepo;
    private final VisitPurposeRepo visitPurposeRepo;
    private final VisitTimeRepo visitTimeRepo;
    
    public SalesVisitService(CustomerRepo customerRepo, 
            CustomerOrderRepo customerOrderRepo, 
            OrderStatusRepo orderStatusRepo, 
            ProductRepo productRepo, 
            RoleRepo roleRepo,
            SalesVisitRepo salesVisitRepo,
            UserRepo userRepo,
            VisitPurposeRepo visitPurposeRepo,
            VisitTimeRepo visitTimeRepo) {
        this.customerRepo = customerRepo;
        this.customerOrderRepo = customerOrderRepo;
        this.orderStatusRepo = orderStatusRepo;
        this.productRepo = productRepo;
        this.roleRepo = roleRepo;
        this.salesVisitRepo = salesVisitRepo;
        this.userRepo = userRepo;
        this.visitPurposeRepo = visitPurposeRepo;
        this.visitTimeRepo = visitTimeRepo;
    }
   
    public SalesVisit findSalesVisitById(int id){
        SalesVisit salesVisit = salesVisitRepo.findById(id).orElse(null);
        return salesVisit;
    }
    
    public List <SalesVisit> findAllSalesVisit(){
        List <SalesVisit> visits = salesVisitRepo.findAll();
        return visits;
    }
    public List <SalesVisit> findSalesVisitsByCustomerId(int id){
        List <SalesVisit> visits = salesVisitRepo.findAll();
        List<SalesVisit> customerVisits = new ArrayList<>();
        for(SalesVisit visit : visits){
        Customer customer = visit.getCustomer();
        int customerId = customer.getCustomer_id();
            if(customerId == id){
                customerVisits.add(visit);
            }
        }
        return customerVisits;
    }
    public List <SalesVisit> findSalesVisitsByEmployeeId(int id){
        List <SalesVisit> visits = salesVisitRepo.findAll();
        List<SalesVisit> employeeVisits = new ArrayList<>();
        for(SalesVisit visit : visits){
        User user = visit.getUser();
        int employeeId = user.getEmployee_id();
            if(employeeId == id){
                employeeVisits.add(visit);
            }
        }
        return employeeVisits;
    }
    
    public void deleteSalesVisitByID(int id){
         salesVisitRepo.deleteById(id);  
    }
    
    public SalesVisit EditSalesVisitById(SalesVisit salesVisit){
        return salesVisitRepo.save(salesVisit);
      
     }
    
    public SalesVisit AddSalesVisitId(SalesVisit salesVisit){
        return salesVisitRepo.save(salesVisit);
    }
    
    public User findUserById(int id){
        User user = userRepo.findById(id).orElse(null);
        return user;
    }
    
    public Customer findCustomerById(int id){
        Customer customer = customerRepo.findById(id).orElse(null);
        return customer;
    }
    
    public List <User> findAllEmployees(){
        List <User> users = userRepo.findAll();
        List <User> employees = new ArrayList<>();
        for(User user : users){
            Set<Role> roles = user.getRoles();
            for(Role role : roles){
               String roleName = role.getRole();
               if(roleName.equals("ROLE_USER")){
                   employees.add(user);
               }
            }
        }
        return employees;
    }
    
    public List<Customer> findAllCustomers(){
        List <Customer> customers = customerRepo.findAll();
        return customers;
    }
//    public List <SalesVisit> findTodaySalesVisitsByEmployeeId(int id){
//        List <SalesVisit> visits = salesVisitRepo.findByUserEmployeeId(id);
//        LocalDateTime today
//        List <SalesVisit> todayVisits = new ArrayList<>();
//        for (SalesVisit visit: visits){
//           LocalDateTime visitDate = visit.getVisit_date();
//           if(visitDate.isEqual())
//        }
//        return visits;
//    }

    public List<VisitPurpose> findAllVisitPurpose() {
        return visitPurposeRepo.findAll();
    }
    
   public VisitPurpose findVisitPurpose(int id) {
        return visitPurposeRepo.findById(id).orElse(null);
    }

    public List<VisitTime> findAllVisitTime() {
        return visitTimeRepo.findAll();
        }
    
    public VisitTime findVisitTime(int timeId) {
        return visitTimeRepo.findById(timeId).orElse(null);
        }
    
}
