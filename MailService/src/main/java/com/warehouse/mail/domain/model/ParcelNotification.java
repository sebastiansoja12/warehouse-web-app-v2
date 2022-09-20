package com.warehouse.mail.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelNotification {
    private String subject;
    private String recipient;
    private String body;
}