/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="role_id", nullable = false)
  private int role_id;

  @Column(name = "role")
  @Size(max = 30, message = "Only 30 characters allowed")
  @NotBlank(message = "Field must not be blank")
  private String role;

  @ManyToMany(mappedBy = "roles")
  private List <User> users;

}
