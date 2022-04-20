package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ParcelRepository extends JpaRepository<Parcel, UUID> {

    List<Parcel> findAll();

    Optional<Parcel> findById(UUID id);

    Parcel getById(UUID id);

}
