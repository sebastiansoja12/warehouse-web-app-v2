package com.warehouse.warehouse.service;

import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import com.warehouse.warehouse.exceptions.ParcelNotFound;
import com.warehouse.warehouse.model.*;
import com.warehouse.warehouse.repository.RouteRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class ParcelService {

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, UserRepository userRepository, RouteRepository routeRepository, AuthService authService, MailService mailService, ParcelExportService parcelExportService){
        this.parcelRepository= parcelRepository;
        this.routeRepository = routeRepository;
        this.authService = authService;
        this.mailService = mailService;
        this.parcelExportService = parcelExportService;
    }
private final ParcelRepository parcelRepository;
    private final RouteRepository routeRepository;
private final AuthService authService;
private final MailService mailService;

private final ParcelExportService parcelExportService;

    @Transactional
    public void save(Parcel parcel) throws Exception {

            Route route = new Route();
            User user = new User();
            user.setId(1);
            route.setParcel(parcel);
            route.setCreated(Instant.now());
            route.setUser(user);
            route.setDepot(initiate());



            routeRepository.save(route);
            parcelRepository.save(parcel);

        mailService.sendNotification(new ParcelNotification( "Została do państwa nadana przesyłka",
                parcel.getEmail(), "Docelowa destynacja paczki to: " + parcel.getDestination_address() + "\n" +
                "Kod Państwa paczki to: " + parcel.getId().toString() + "\nWciśnij poniższy link by pobrać atykietę: " +
                 "\n" + "http://localhost:8080/api/parcels/toPDF/" + parcel.getId().toString()));


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

    public void exportParcelToPdfById(HttpServletResponse response, UUID id) throws Exception {

        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFound("Paczka o id " + id.toString() + " nie zostala znaleziona"));
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new java.util.Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=parcel_id" + parcel.getId() + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToPdf(response, parcel);

    }

    public void exportParcelToCSVById(HttpServletResponse response, UUID id) throws DocumentException,IOException {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFound("Paczka o id " + id.toString() + " nie zostala znaleziona"));
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=parcel_id" + parcel.getId() + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToCSV(response, parcel);
    }

}
