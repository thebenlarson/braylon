/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.controllers;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.entities.CustomerOrder;
import com.braylon.Braylon.entities.OrderStatus;
import com.braylon.Braylon.entities.Product;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.service.CustomerOrderService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerOrderController {

    @Autowired
    CustomerOrderService service;

    @GetMapping("customerOrders")
    public String newOrder(String id, Model model) {

        CustomerOrder order = new CustomerOrder();
        int num = Integer.parseInt(id);
        User user = service.getUserById(num);
        List<OrderStatus> statusList = service.getUserStatuses();
        List<Product> productList = service.getProducts();
        List<Customer> customerList = service.getCustomer();
        List<CustomerOrder> salesRepOrders = service.getOrdersByEmployeeId(user);

        model.addAttribute("salesRepOrder", salesRepOrders);
        model.addAttribute("customerOrder", order);
        model.addAttribute("customers", customerList);
        model.addAttribute("products", productList);
        model.addAttribute("statuses", statusList);
        model.addAttribute("user", user);

        return "customerOrders/customerOrders";
    }

    @PostMapping("customerOrders")
    public String newOrder(CustomerOrder order, BindingResult result, HttpServletRequest request) {

        int productId = Integer.parseInt(request.getParameter("product"));
        order.setProduct(service.getProductById(productId));

        int statusId = Integer.parseInt(request.getParameter("status"));
        order.setStatus(service.getStatusById(statusId));

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = service.getUserById(userId);
        order.setUser(user);

        int customerId = Integer.parseInt(request.getParameter("customer"));
        order.setCustomer(service.getCustomerById(customerId));

        service.saveCustomerOrder(order);
        return "redirect:customerOrders/customerOrders?employee_Id=" + user.getEmployee_id();
    }

    @GetMapping("customerOrderEdit")
    public String editOrder(int id, Model model) {

        CustomerOrder order = service.getOrderById(id);
        List<OrderStatus> statusList = service.getUserStatuses();
        List<Product> productList = service.getProducts();
        List<Customer> customerList = service.getCustomer();

        model.addAttribute("order", order);
        model.addAttribute("statuses", statusList);
        model.addAttribute("products", productList);
        model.addAttribute("customers", customerList);
        
        return "customerOrders/customerOrderEdit";
    }

}
