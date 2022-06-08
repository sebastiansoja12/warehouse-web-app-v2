package com.warehouse.repository;

import com.warehouse.model.PaymentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInformation, Long> {
    List<PaymentInformation> findAll();

    PaymentInformation findByPaypalId(String paypalId);

    PaymentInformation findByParcel_id(UUID id);
}
