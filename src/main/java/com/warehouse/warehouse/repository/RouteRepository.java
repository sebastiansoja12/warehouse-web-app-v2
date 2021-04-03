package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {

    @Query(value = "SELECT * from Route order by created desc", nativeQuery = true)
    List<Route> findAll();

    List<Route> findFirst10ByUser_usernameOrderByCreatedDesc(String username);

    List<Route> findByParcelId(final UUID id);

    Route findByParcel_IdAndUser(UUID parcel_id, User user);


    Optional<List<Route>> findAllByParcel_IdOrderByCreatedDesc(final UUID id);

    void deleteByParcelIdAndDepot_DepotCode(final UUID parcelId, final String depotCode);

}
