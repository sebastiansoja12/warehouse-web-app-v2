package com.warehouse.route.infrastructure.adapter.secondary;

import com.warehouse.route.domain.model.Route;
import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.domain.port.secondary.RouteRepository;
import com.warehouse.route.infrastructure.adapter.secondary.entity.*;
import com.warehouse.route.infrastructure.adapter.secondary.exception.DepotNotFoundException;
import com.warehouse.route.infrastructure.adapter.secondary.exception.ParcelNotFoundException;
import com.warehouse.route.infrastructure.adapter.secondary.exception.SupplierNotFoundException;
import com.warehouse.route.infrastructure.adapter.secondary.exception.UserNotFoundException;
import com.warehouse.route.infrastructure.adapter.secondary.mapper.RouteModelMapper;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class RouteRepositoryImpl implements RouteRepository {

    private final RouteReadRepository routeReadRepository;

    private final RouteModelMapper mapper;

    private final RouteDepotReadRepository routeDepotReadRepository;

    private final ParcelReadRepository parcelReadRepository;

    private final SupplierReadRepository supplierReadRepository;

    private final UserReadRepository userReadRepository;

    @Override
    public List<Route> findByParcelId(Long parcelId) {

        final List<RouteEntity> routeEntity = routeReadRepository.findByParcelId(parcelId);

        return mapper.map(routeEntity);
    }

    @Override
    public void initializeRoute(Route route) {
        final ParcelEntity parcelEntity = parcelReadRepository
                .findById(route.getParcelId()).orElseThrow( () -> new ParcelNotFoundException("Parcel does not exist"));

        final RouteEntity routeEntity = RouteEntity.builder()
                .created(route.getCreated())
                .parcel(parcelEntity)
                .build();

        routeReadRepository.save(routeEntity);
    }

    @Override
    public RouteResponse saveSupplyRoute(Route route) {
        final ParcelEntity parcelEntity = parcelReadRepository.findById(route.getParcelId())
                .orElseThrow( () -> new ParcelNotFoundException("Parcel does not exist"));
        final SupplierEntity supplierEntity = supplierReadRepository
                .findBySupplierCode(route.getSupplierCode()).orElseThrow(
                        () -> new SupplierNotFoundException("Supplier does not exist"));
        final RouteEntity routeEntity = RouteEntity.builder()
                .created(route.getCreated())
                .parcel(parcelEntity)
                .supplier(supplierEntity)
                .build();

        return mapper.mapToRouteResponse(routeReadRepository.save(routeEntity));
    }

    @Override
    public RouteResponse save(Route route) {
        final DepotEntity depotEntity = findDepotEntity(route);
        final ParcelEntity parcelEntity = findParcelEntity(route);
        final SupplierEntity supplierEntity = findSupplierEntity(route);
        final UserEntity userEntity = findUserEntity(route);

        final RouteEntity routeEntity = RouteEntity.builder()
                .created(route.getCreated())
                .depot(depotEntity)
                .parcel(parcelEntity)
                .user(userEntity)
                .supplier(supplierEntity)
                .build();


        final RouteEntity entityToSave = routeReadRepository.save(routeEntity);

        return mapper.mapToRouteResponse(entityToSave);
    }

    @Override
    public List<Route> findByUsername(String username) {
        return mapper.map(routeReadRepository.findAllByUserUsername(username));
    }

    @Override
    public void deleteByParcelIdAndDepotCodeAndUsername(Long id, String depotCode, String username) {
        routeReadRepository.deleteByParcelIdAndDepot_DepotCodeAndUser_Username(id, depotCode, username);
    }

    private ParcelEntity findParcelEntity(Route route) {
        return parcelReadRepository.findById(route.getParcelId())
                .orElseThrow(() -> new ParcelNotFoundException("Parcel does not exist"));
    }


    public DepotEntity findDepotEntity(Route route) {
        return routeDepotReadRepository.findByDepotCode(route.getDepotCode())
                .orElseThrow(() -> new DepotNotFoundException("Depot was not found")
        );
    }

    private SupplierEntity findSupplierEntity(Route route) {
        return supplierReadRepository
                .findBySupplierCode(route.getSupplierCode()).orElse(null);
    }

    private UserEntity findUserEntity(Route route) {
        return userReadRepository.findByUsername(route.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User was not found")
        );
    }
}
