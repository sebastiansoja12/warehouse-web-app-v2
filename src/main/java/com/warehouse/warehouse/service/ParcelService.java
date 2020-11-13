package com.warehouse.warehouse.service;

import com.warehouse.warehouse.data.ProductDataAccessService;
import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class ParcelService {

private final ParcelRepository parcelRepository;
private final ProductDataAccessService productDataAccessService;
private final UserRepository userRepository;

    @Transactional
    public Parcel save( LoginRequest loginRequest, Parcel parcel){
        parcel.setCreatedAt(Instant.now());
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        userRepository.save(user);
        return parcelRepository.save(parcel);
    }

    @Transactional(readOnly = true)
    public List<Parcel> findAll(){
        return parcelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parcel findByParcelCode(String parcelCode) {
        return parcelRepository.findByParcelCode(parcelCode).orElse(null);
    }


    @Transactional
    public void update(Parcel parcel, String kod) {

    }
}
