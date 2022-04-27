package com.warehouse.warehouse.model;


import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.warehouse.enumeration.ParcelStatus;
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
public class PaymentInformation {

    @Id
    @GeneratedValue()
    private Long id;

    private double amount;

    private String paypalId;

    private PaymentPass paymentPass;

    private ParcelStatus parcelStatus;

    private String paymentUrl;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Parcel parcel;



}
