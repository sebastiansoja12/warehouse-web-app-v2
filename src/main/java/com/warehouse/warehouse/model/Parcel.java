package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warehouse.warehouse.enumeration.ParcelType;
import com.warehouse.warehouse.enumeration.PaymentPass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Recipient recipient;

    private double price;

    private boolean isCustom;

    private ParcelType parcelType;

}
