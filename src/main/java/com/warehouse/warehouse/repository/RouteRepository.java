package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {

    List<Route> findAll();

    List<Route> findByUser_username(String username);

    List<Route> findByParcelId(final UUID id);

    Route findByParcel_IdAndUser(UUID parcel_id, User user);

    List<Route> findRoutesByUser_Username(String username);


    Optional<List<Route>> findAllByParcel_IdOrderByCreated(final UUID id);

    void deleteByParcelIdAndDepot_DepotCode(final UUID parcelId, final String depotCode);


}
