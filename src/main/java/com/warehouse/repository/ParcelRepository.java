package com.warehouse.repository;

import com.warehouse.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    List<Parcel> findAll();

    Optional<Parcel> findById(Long id);

    Optional<Parcel> findByIdAndSenderEmail(Long id, String senderEmail);

    Parcel getById(Long id);

}
