/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.repositories;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.entities.CustomerOrder;
import com.braylon.Braylon.entities.User;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {

    public List<CustomerOrder> findAllByUser(User user, Sort sort);

    public List<CustomerOrder> findAllByCustomer(Customer customer, Sort sort);

}
