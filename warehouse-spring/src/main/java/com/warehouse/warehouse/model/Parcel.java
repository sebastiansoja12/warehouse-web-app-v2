package com.warehouse.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Parcel {

    @Id
    @GeneratedValue()
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID id;


    @Valid
    @NotBlank
    private String firstName;

    @Valid
    @NotBlank
    private String lastName;

    @Valid
    @NotBlank
    private String senderTelephone;

    @Valid
    @NotBlank
    private String senderEmail;

    @Valid
    @NotBlank
    private String recipientEmail;

    @Valid
    @NotBlank
    private String recipientTelephone;

    @Valid
    @NotBlank
    private String recipientFirstName;

    @Valid
    @NotBlank
    private String recipientLastName;

    @Valid
    @NotBlank
    private String recipientCity;

    @Valid
    @NotBlank
    private String recipientStreet;


    private boolean isCustom;


}
