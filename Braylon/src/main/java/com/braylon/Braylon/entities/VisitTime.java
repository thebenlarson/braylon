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
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DEV10
 */
@Entity
@Data
public class VisitTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int time_id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private String time_name;   
}
