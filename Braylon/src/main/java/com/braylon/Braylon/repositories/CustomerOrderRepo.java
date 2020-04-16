/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.repositories;

import com.braylon.Braylon.entities.CustomerOrder;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {

    public List<CustomerOrder> findAllByEmployee_Id(int employee_id, Sort sort);

    public List<CustomerOrder> findAllByCustomer_Id(int customer_id, Sort sort);

}
