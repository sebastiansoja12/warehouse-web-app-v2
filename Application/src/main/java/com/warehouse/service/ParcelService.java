package com.warehouse.service;

import com.lowagie.text.DocumentException;
import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.config.ApplicationUrlConfig;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.ParcelNotification;
import com.warehouse.exceptions.ParcelNotFound;
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

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final MailService mailService;
    private final ParcelExportService parcelExportService;
    private final PaymentService paymentService;

    private final ApplicationUrlConfig config;


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
                        "\n" + config.springUrl + "/api/parcels/" + parcel.getId().toString() + "/label" +
                          "\nAby opłacić przesyłkę prosimy wcisnąć w link: " + payment
                            + "\nAby zarządzać przesyłką prosimy wejść w link: " + config.guiUrl +
                        "/shipment/client/management/" + parcel.getId().toString()));

        return payment;

    }


    @Transactional(readOnly = true)
    public List<Parcel> findAll() {
        return parcelRepository.findAll();
    }

    public Parcel findById(Long id) {
        return parcelRepository.findById(id).orElseThrow( () -> new ParcelNotFound("Paczka nie zostala znaleziona"));
    }
    public void exportParcelToPdfById(HttpServletResponse response, Long id) throws Exception {

        final Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFound("Paczka o id " + id + " nie zostala znaleziona"));
        response.setContentType("application/pdf");
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        final String currentDateTime = dateFormatter.format(new java.util.Date());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=" + parcel.getId() + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToPdf(response, parcel);

    }

    public void exportParcelToCSVById(HttpServletResponse response, Long id) throws DocumentException, IOException {
        final Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFound("Paczka o id " + id + " nie zostala znaleziona"));
        response.setContentType("text/csv");
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        final String currentDateTime = dateFormatter.format(new Date());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=parcel_id" + parcel.getId() + "_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToCSV(response, parcel);
    }

}
