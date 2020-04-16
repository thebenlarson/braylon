/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.entities.CustomerOrder;
import com.braylon.Braylon.entities.OrderStatus;
import com.braylon.Braylon.entities.Product;
import com.braylon.Braylon.entities.Role;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.repositories.CustomerOrderRepo;
import com.braylon.Braylon.repositories.CustomerRepo;
import com.braylon.Braylon.repositories.OrderStatusRepo;
import com.braylon.Braylon.repositories.ProductRepo;
import com.braylon.Braylon.repositories.RoleRepo;
import com.braylon.Braylon.repositories.UserRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

    @Autowired
    CustomerRepo customers;
    @Autowired
    CustomerOrderRepo orders;
    @Autowired
    ProductRepo products;
    @Autowired
    OrderStatusRepo statuses;
    @Autowired
    UserRepo users;
    @Autowired
    RoleRepo roles;

//    public CustomerOrder saveCustomerOrder(CustomerOrder order) {
//        return orders.save(order);
//    }
//
//    public List<OrderStatus> getStatuses() {
//        return statuses.findAll();
//    }
//
//    public List<OrderStatus> getUserStatuses() {
//        List<OrderStatus> complete = statuses.findAll();
//        return complete.stream()
//                .filter(status -> status.getStatus_name().equals("Cancelled"))
//                .filter(status -> status.getStatus_name().equals("Approved"))
//                .filter(status -> status.getStatus_name().equals("Completed"))
//                .collect(Collectors.toList());
//
//    }
//
//    public List<Product> getProducts() {
//        return products.findAll();
//    }
//
//    public List<Customer> getCustomer() {
//        return customers.findAll();
//    }
//
//    public List<CustomerOrder> getOrdersByEmployeeId(User user) {
//
//        if (user.getRoles().contains(roles.getOne(1))) {
//            return orders.findAll();
//        }
//
//        return orders.findAllByEmployee_Id(user.getEmployee_id(), Sort.by(Sort.Direction.ASC, "order_id"));
//    }
//
//    public List<CustomerOrder> getAllOrders() {
//        return orders.findAll(Sort.by(Sort.Direction.ASC, "order_id"));
//    }
//
//    public List<CustomerOrder> getOrdersByCustomerId(Customer customer) {
//        return orders.findAllByCustomer_Id(customer.getCustomer_id(), Sort.by(Sort.Direction.ASC, "order_id"));
//    }
//
//    public CustomerOrder getOrderById(int order_id) {
//        return orders.getOne(order_id);
//    }
//
//    public User getUserById(int id) {
//        return users.getOne(id);
//    }
//
//    public Product getProductById(int id) {
//        return products.getOne(id);
//    }
//
//    public OrderStatus getStatusById(int id) {
//        return statuses.getOne(id);
//    }
//
//    public Customer getCustomerById(int id) {
//        return customers.getOne(id);
//    }
}
