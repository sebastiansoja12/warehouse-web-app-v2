package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.DepotInformationRepository;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import com.warehouse.warehouse.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ParcelService {

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, UserRepository userRepository, DepotRepository depotRepository){
        this.parcelRepository= parcelRepository;
        this.userRepository= userRepository;
        this.depotRepository=depotRepository;
    }
private final ParcelRepository parcelRepository;
private final UserRepository userRepository;
private final DepotRepository depotRepository;

    @Transactional
    public Parcel save(Parcel parcel){
        Depot depot = new Depot();
        DepotInformation depotInformation= new DepotInformation();
        depotInformation.setId((long) 7);
        depot.setDepotInformation(depotInformation);
        depot.setParcel(parcel);
        depot.setCreated(Instant.now());
        depot.setUser(usernameToId());
        depotRepository.save(depot);
        return parcelRepository.save(parcel);
    }



    public String auth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return  authentication.getName();
        }
        else {
            return null;
        }
    }
public User usernameToId(){
    Optional<User> userOptional = userRepository.getUsersIdByUsername(auth());
    User user = userOptional.orElseThrow(null);
    return user;
}
    @Transactional(readOnly = true)
    public List<Parcel> findAll(){
        return parcelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parcel findByParcelCode(String parcelCode) {
        return parcelRepository.findByParcelCode(parcelCode).orElse(null);
    }
}
