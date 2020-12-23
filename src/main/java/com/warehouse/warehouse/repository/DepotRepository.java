package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepotRepository extends JpaRepository<Depot,Long> {

    @Query(value = "SELECT * from Depot order by created desc", nativeQuery = true)
    List<Depot> findAll();

    List<Depot> findByParcelId(final UUID id);


    List<Depot> findAllByParcel_ParcelCodeOrderByCreatedDesc(final String parcelCode);

    void deleteByParcelId( final UUID parcelId);

}
