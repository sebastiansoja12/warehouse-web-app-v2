package com.warehouse.warehouse.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;
import java.util.Set;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("nazwaUzytkownika")
    private String username;

    @JsonProperty( value= "haslo", access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @JsonProperty( value= "imie")
    private String firstName;

    @JsonProperty( value= "nazwisko", access = JsonProperty.Access.WRITE_ONLY)
    private String lastName;

    @Email
    @JsonProperty( value= "email", access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    //@NotEmpty(message="Rola jest wymagana")
    @JsonProperty( value= "rola", access = JsonProperty.Access.WRITE_ONLY)
    private String role;



    @JsonProperty( value= "czyAktywowane", access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;



    public User(String username){
        this.username= username;
    }


}

