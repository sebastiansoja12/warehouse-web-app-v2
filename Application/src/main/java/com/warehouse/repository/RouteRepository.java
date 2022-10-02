package com.warehouse.repository;

import com.warehouse.entity.Route;
import com.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {

    List<Route> findAll();

    List<Route> findByUser_username(String username);

    List<Route> findByParcelId(final Long id);

    Route findByParcel_IdAndUser(Long parcel_id, User user);


    Optional<List<Route>> findAllByParcel_IdOrderByCreated(final Long id);

    void deleteByParcelIdAndDepot_DepotCode(final Long parcelId, final String depotCode);


}
