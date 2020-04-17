/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author DEV10
 */
@Entity
@Data
public class VisitPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int purpose_id;
   
   @Column(nullable = false)
   @NotBlank(message = "Purpose name must not be empty.")
   @Size(max = 100, message = "Purpose name must be less than 100 characters.")
   private String purpose_name;
}