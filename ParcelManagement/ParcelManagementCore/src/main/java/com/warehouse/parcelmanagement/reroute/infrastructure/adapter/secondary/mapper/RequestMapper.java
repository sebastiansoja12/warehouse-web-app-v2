package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper;

import com.warehouse.mail.domain.vo.Notification;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RequestMapper {

    @Mapping(source = "parcelId", target = "parcelId")
    ParcelId map(RerouteRequest rerouteRequest);

    Notification map(RerouteNotification rerouteNotification);

}
