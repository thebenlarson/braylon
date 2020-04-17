/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.repositories;

import com.braylon.Braylon.entities.SalesVisit;
import com.braylon.Braylon.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesVisitRepo extends JpaRepository<SalesVisit, Integer> {
    List<SalesVisit> findAllByUser(User user);
}
