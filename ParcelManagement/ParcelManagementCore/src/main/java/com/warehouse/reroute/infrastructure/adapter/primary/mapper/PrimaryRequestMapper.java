package com.warehouse.reroute.infrastructure.adapter.primary.mapper;

import com.warehouse.reroute.domain.model.Parcel;
import com.warehouse.reroute.domain.model.RerouteRequest;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.infrastructure.api.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PrimaryRequestMapper {


    @Mapping(source = "request.parcelId.value", target = "parcelId")
    @Mapping(source = "request.email.value", target = "email")
    RerouteRequest map(RerouteRequestDto request);

    @Mapping(source = "parcelId.value", target = "id")
    @Mapping(source = "parcel", target = "parcel")
    @Mapping(source = "token.value", target = "token")
    UpdateParcelRequest map(UpdateParcelRequestDto updateParcelRequestDto);

    Parcel map(ParcelDto parcelDto);

    Token map(TokenDto tokenDto);

    @Mapping(source = "value", target = "value")
    ParcelId map(ParcelIdDto parcelIdDto);
}
