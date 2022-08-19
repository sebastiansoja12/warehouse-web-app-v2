package com.warehouse.service;

import com.lowagie.text.DocumentException;
import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.ParcelNotification;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final MailService mailService;
    private final ParcelExportService parcelExportService;
    private final PaymentService paymentService;

    private final String url = "http://localhost:8080";

    private final String managementUrl = "http://localhost:4200";


    @Transactional
    public String save(Parcel parcel) throws HttpMessageNotReadableException, PayPalRESTException {
        parcel.setPrice(parcel.getParcelType().getPrice());
        parcelRepository.save(parcel);
        final String payment = paymentService.payment(parcel);
        mailService.sendNotification(new ParcelNotification
                ("Została przez Państwa nadana przesyłka ",
                        parcel.getRecipientEmail(), "Docelowa destynacja paczki to: " +
                        parcel.getRecipientCity() + ", "
                        + parcel.getRecipientStreet() + "\n" +
                        "Kod Państwa paczki to: " + parcel.getId().toString()
                        + "\nProsimy wejść w poniższy link by pobrać etykietę: " +
                        "\n" + url + "/api/parcels/" + parcel.getId().toString() + "/label" +
                          "\nAby opłacić przesyłkę prosimy wcisnąć w link: " + payment
                            + "\nAby zarządzać przesyłką prosimy wejść w link: " + managementUrl +
                        "/parcel/client/management/" + parcel.getId().toString()));

        return payment;

    }


    @Transactional(readOnly = true)
    public List<Parcel> findAll() {
        return parcelRepository.findAll();
    }

    public Parcel findById(UUID id) {
        return parcelRepository.findById(id).orElseThrow( () -> new ParcelNotFound("Paczka nie zostala znaleziona"));
    }
    public void exportParcelToPdfById(HttpServletResponse response, UUID id) throws Exception {

        final Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFound("Paczka o id " + id + " nie zostala znaleziona"));
        response.setContentType("application/pdf");
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        final String currentDateTime = dateFormatter.format(new java.util.Date());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=" + parcel.getId() + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToPdf(response, parcel);

    }

    public void exportParcelToCSVById(HttpServletResponse response, UUID id) throws DocumentException, IOException {
        final Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFound("Paczka o id " + id + " nie zostala znaleziona"));
        response.setContentType("text/csv");
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        final String currentDateTime = dateFormatter.format(new Date());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=parcel_id" + parcel.getId() + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToCSV(response, parcel);
    }

}
