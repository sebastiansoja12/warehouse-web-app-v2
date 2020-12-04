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
    List<Depot> findAll();

    List<Depot> findByParcelId(final UUID id);

   /* @Query("select d.created_at, d.parcel.id, p.parcelCode, di.city  " +
            "from Depot d join Parcel p on d.parcel.id= p.id " +
            " join DepotInformation di on d.depotInformation.id= di.id " +
            "where p.parcelCode = ?1 ORDER BY d.created_at DESC")

    */
    List<Depot> findAllByParcel_ParcelCodeOrderByCreatedDesc(final String parcelCode);

    void deleteByParcelId( final UUID parcelId);

}
