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
    @GeneratedValue()
    private int userId;

    @JsonProperty("nazwaUzytkownika")
    private String username;

    @JsonProperty("haslo")
    private String password;


    @JsonProperty("imie")
    private String firstName;

    @JsonProperty("nazwisko")
    private String lastName;

    @Email
    private String email;

    @NotEmpty(message="Rola jest wymagana")
    @JsonProperty("rola")
    private String role;



    @JsonProperty("czyAktywowane")
    private boolean enabled;


    public User(String username){
        this.username= username;
    }


}

