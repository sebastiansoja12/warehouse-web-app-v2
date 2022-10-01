package com.warehouse.parcelmanagement.reroute.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateParcelRequest {

    private Long id;
    private Parcel parcel;
    private Integer token;

}