/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.repositories;

import com.braylon.Braylon.entities.VisitPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEV10
 */
@Repository
public interface VisitPurposeRepo extends JpaRepository<VisitPurpose, Integer>{
    
}
