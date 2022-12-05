package com.warehouse.route.infrastructure.adapter.secondary.mapper;

import com.warehouse.route.domain.model.Route;
import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.infrastructure.adapter.secondary.entity.RouteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RouteModelMapper {

    @Mapping(source = "parcel.id", target = "parcelId")
    @Mapping(source = "depot.depotCode", target = "depotCode")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "supplier.supplierCode", target = "supplierCode")
    Route map(RouteEntity routeEntity);

    List<Route> map(List<RouteEntity> routeEntityList);

    @Mapping(target = "parcel.id", source = "parcelId")
    @Mapping(target = "supplier.supplierCode", source = "supplierCode")
    @Mapping(target = "depot.depotCode", source = "depotCode")
    @Mapping(target = "user.username", source = "username")
    RouteEntity map(Route route);


    @Mapping(source = "id", target = "id")
    RouteResponse mapToRouteResponse(RouteEntity routeEntity);

}
