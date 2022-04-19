package com.warehouse.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

    public Order(double price, String method, String intent, String description) {
        this.price = price;
        this.currency = getCurrency();
        this.method = method;
        this.intent = intent;
        this.description = description;
    }

    public String getCurrency() {
        return "PLN";
    }
}