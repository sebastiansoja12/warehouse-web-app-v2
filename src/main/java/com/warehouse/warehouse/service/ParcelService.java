package com.warehouse.warehouse.service;

import com.google.zxing.WriterException;
import com.warehouse.warehouse.model.*;
import com.warehouse.warehouse.repository.RouteRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class ParcelService {

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, UserRepository userRepository, RouteRepository routeRepository, AuthService authService, MailService mailService){
        this.parcelRepository= parcelRepository;
        this.routeRepository = routeRepository;
        this.authService = authService;
        this.mailService = mailService;
    }
private final ParcelRepository parcelRepository;
    private final RouteRepository routeRepository;
private final AuthService authService;
private final MailService mailService;

    @Transactional
    public void save(Parcel parcel) throws IOException, WriterException {

            Route route = new Route();
            User user = new User();
            user.setId(1);
            route.setParcel(parcel);
            route.setCreated(Instant.now());
            route.setUser(user);
            route.setDepot(initiate());



            routeRepository.save(route);
             parcelRepository.save(parcel);

        CodeService.generateQRCodeImage(parcel.getId().toString());
        CodeService.getQRCodeImage(parcel.getId().toString());

        mailService.sendNotification(new ParcelNotification( "Została do państwa nadana przesyłka",
                parcel.getEmail(), "Docelowa destynacja paczki to: " + parcel.getDestination_address() + "\n" +
                "Kod Państwa paczki to: " + parcel.getId().toString() + "\nWciśnij poniższy link by pobrać atykietę: " +
                 "\n" + "http://localhost:8080/generateQRCode/" + parcel.getId().toString()));


    }

    public Depot initiate(){
        Depot depot = new Depot();
        depot.setId((long)9);
        depot.setCity("Paczka wkrótce zostanie odebrana przez kuriera");
        depot.setCountry("Nadanie");
        depot.setStreet("-");
        return depot;
    }

    @Transactional(readOnly = true)
    public List<Parcel> findAll(){
        return parcelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parcel findById(UUID id) {
        if(parcelRepository.exists(Example.of(parcelRepository.findById(id).orElseThrow()))){
            return parcelRepository.findById(id).orElseThrow();

        }
        return null;
    }
}
