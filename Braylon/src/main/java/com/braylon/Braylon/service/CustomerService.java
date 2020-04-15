package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.repositories.CustomerRepo;
import java.util.Collection;
import java.util.Optional;
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
    
    
}
