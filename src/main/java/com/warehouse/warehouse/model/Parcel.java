package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Parcel {

    @Id
    @GeneratedValue()
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;


    @Valid
    @NotBlank
    private String firstName;

    @Valid
    @NotBlank
    private String lastName;

    @Valid
    @NotBlank
    private String sender_telephone;

    @Valid
    @NotBlank
    private String destination_telephone;

    @Valid
    @NotBlank
    private String destination_address;

    @Valid
    @NotBlank
    private String email;

    private boolean isCustom;





}
