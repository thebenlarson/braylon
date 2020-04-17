/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int customer_id;
    
    private String company;
    
    private String contact;
    
    private String address;
    
    private String city;
    
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    
    private String phone;
    
    private String email;
    
    @ManyToOne
    @JoinColumn( name = "employee_id")
    private User user;
    
    //access list of orders for the customer
    @OneToMany( mappedBy = "customer")
    private List<CustomerOrder> customerOrders;
    
    
}
