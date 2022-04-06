package com.warehouse.warehouse.model;


import com.warehouse.warehouse.enumeration.PaymentPass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue()
    private Long id;

    private double amount;

    private PaymentPass paymentPass;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Parcel parcel;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Customer customer;



}
