package com.braylon.Braylon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Amir Jamal
 */

@Entity
@Data
public class State {
    
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    @Column(name = "state_name")
    private String name;

    @Column(name = "state_abrv")
    private String abrv;

}
