package com.warehouse.paypal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayeeInformation {
    private String email;
    private String merchantId;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private String telephoneNumber;
}
