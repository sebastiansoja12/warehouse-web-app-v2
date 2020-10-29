package com.warehouse.warehouse.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class User {

    @Id
    @GeneratedValue(strategy=AUTO)
    private int userId;

    @NotBlank(message="Username cannot be empty")
    private String username;

    @NotBlank(message="Password cannot be empty")
    private String password;

  //  @NotBlank(message="First name cannot be empty")
    private String firstName;

  //  @NotBlank(message="Last name cannot be empty")
    private String lastName;

    @Email
    @NotEmpty(message="Email is required")
    private String email;

    @NotEmpty(message="Role is required")
    private String role;


    private boolean enabled;


}

