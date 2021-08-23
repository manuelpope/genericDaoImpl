package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USER_SECURITY")
public class UserCustom implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "could not be blank field name")
  private String nameUser;

  @NotEmpty(message = "could not be blank field name")
  private String password;

  private String role;

  public UserCustom(String nameUser, String password) {
    this.nameUser = nameUser;
    this.password = password;
  }
}
