package com.warehouse.reroute.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateParcelRequest {

    Long id;

    Parcel parcel;

    Integer token;

}