package com.warehouse.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaczkaRequest {

    private UUID orderId;
    private boolean isCustom;

}