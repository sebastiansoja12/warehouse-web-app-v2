package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.Parcel;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PrimaryRequestMapper {


    @Mapping(source = "request.parcelId.value", target = "parcelId")
    @Mapping(source = "request.email.value", target = "email")
    RerouteRequest map(RerouteRequestDto request);

    @Mapping(source = "parcelId.parcelId", target = "id")
    @Mapping(source = "parcel", target = "parcel")
    @Mapping(source = "token.value", target = "token")
    UpdateParcelRequest map(UpdateParcelRequestDto updateParcelRequestDto);

    Parcel map(ParcelDto parcelDto);

    Token map(TokenDto tokenDto);

    @Mapping(source = "parcelId", target = "parcelId")
    ParcelId map(ParcelIdDto parcelIdDto);
}
