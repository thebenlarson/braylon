/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.entities;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //@Size(max = 9, message = "Only 9 characters allowed")
  private int employee_id;
    
  private String username;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(nullable = false, length = 25)
  private String password;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
          joinColumns = {
            @JoinColumn(name = "employee_id")},
          inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
  private Set<Role> roles;

  @Column(name = "first_name")
  @Size(max = 25, message = "Only 25 characters allowed")
  @NotBlank(message = "Field must not be blank")
  private String firstName;

  @Column(name = "last_name")
  @Size(max = 25, message = "Only 25 characters allowed")
  @NotBlank(message = "Field must not be blank")
  private String lastName;

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
}
