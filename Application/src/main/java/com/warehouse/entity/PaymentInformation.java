package com.warehouse.entity;


import com.warehouse.enumeration.ParcelStatus;
import com.warehouse.enumeration.PaymentPass;
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
@Table(name = "paypal")
public class PaymentInformation {

    @Id
    @GeneratedValue()
    private Long id;

    private double amount;

    private String paypalId;

    private PaymentPass paymentPass;

    private ParcelStatus parcelStatus;

    private String paymentUrl;


    @OneToOne(orphanRemoval = true)
    private Parcel parcel;



}
