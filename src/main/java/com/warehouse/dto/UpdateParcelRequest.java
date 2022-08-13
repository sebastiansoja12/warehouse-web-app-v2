package com.warehouse.dto;


import com.warehouse.entity.Parcel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParcelRequest {

    private UUID id;
    private Parcel parcel;
    private Integer token;

}
