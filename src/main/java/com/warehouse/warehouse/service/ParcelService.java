package com.warehouse.warehouse.service;

import com.warehouse.warehouse.data.ParcelDataAccessService;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class ParcelService {

private final ParcelRepository parcelRepository;
private final ParcelDataAccessService parcelDataAccessService;
private final UserRepository userRepository;
private final DepotRepository depotRepository;
private final DepotInformationRepository depotInformationRepository;
private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;



    @Transactional
    public Parcel save(Parcel parcel){
        Depot depot = new Depot();
        DepotInformation depotInformation= new DepotInformation();
        depotInformation.setId((long)3);
        depot.setDepotInformation(depotInformation);
        depot.setParcel(parcel);
        depot.setCreated_at(Instant.now());
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


    @Transactional
    public void update(Parcel parcel, String kod) {
    }



}
