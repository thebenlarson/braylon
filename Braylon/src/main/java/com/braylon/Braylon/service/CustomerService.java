package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.repositories.CustomerRepo;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Amir Jamal
 */
@Service
public class CustomerService {
    
     /**
     * Injected user repository
     */
    @Autowired
    private CustomerRepo customerRepo;
    
    public Collection<Customer> findAll() {
        return this.customerRepo.findAll();
    }
    
    public Optional<Customer> findById(int id) {
        return this.customerRepo.findById(id);
    }
    
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }
    
    public void delete(Customer customer) {
        this.customerRepo.delete(customer);
    }
    
    public void deleteById(int id) {
        this.customerRepo.deleteById(id);
    }
    
    public List<Customer> findByUser_Id (int id){
        //find all customers
        return this.customerRepo.findAll()
                
                //stream java class
                .stream()
                
                //using the stream class use filter which
                //takes id of user related to the customer compares it to id param
                .filter(customer->customer.getUser().getEmployee_id() == id)
                
                //collect everything from the stream as a list
                .collect(Collectors.toList());
    }
    
    
}
