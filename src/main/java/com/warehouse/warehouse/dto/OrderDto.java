package com.warehouse.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
}
