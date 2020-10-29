package com.warehouse.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaczkaRequest {

    private int orderId;
    private boolean isCustom;

}