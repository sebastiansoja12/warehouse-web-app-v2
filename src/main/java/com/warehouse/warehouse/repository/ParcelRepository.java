package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ParcelRepository extends JpaRepository<Parcel,Long> {

    List<Parcel> findAll();
    Optional<Parcel> findByParcelCode(String parcelCode);
}
