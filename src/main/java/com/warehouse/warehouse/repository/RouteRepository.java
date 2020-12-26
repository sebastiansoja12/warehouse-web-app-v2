package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route,Long> {

    @Query(value = "SELECT * from Route order by created desc", nativeQuery = true)
    List<Route> findAll();

    List<Route> findByParcelId(final UUID id);


    List<Route> findAllByParcel_IdOrderByCreatedDesc(final UUID id);

    void deleteByParcelIdAndDepot_Id(final UUID parcelId, final Long depot_id);

}
