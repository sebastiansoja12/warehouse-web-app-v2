package com.warehouse.warehouse.service;

import com.lowagie.text.DocumentException;
import com.warehouse.warehouse.enumeration.ParcelType;
import com.warehouse.warehouse.enumeration.PaymentStatus;
import com.warehouse.warehouse.exceptions.ParcelNotFound;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.ParcelNotification;
import com.warehouse.warehouse.model.Payment;
import com.warehouse.warehouse.repository.CustomerRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
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
    private final CustomerRepository customerRepository;
    private final MailService mailService;
    private final ParcelExportService parcelExportService;

    @Transactional
    public void save(Parcel parcel) throws Exception {

        parcel.setPaymentStatus(PaymentStatus.NOT_PAID);
        parcel.setParcelType(ParcelType.AVERAGE);
        parcel.setPrice(parcel.getParcelType().getPrice());
        parcelRepository.save(parcel);

        mailService.sendNotification(new ParcelNotification
                ("Została przez Państwa nadana przesyłka ",
                        parcel.getRecipientEmail(), "Docelowa destynacja paczki to: " +
                        parcel.getRecipientCity() + ", "
                        + parcel.getRecipientStreet() + "\n" +
                        "Kod Państwa paczki to: " + parcel.getId().toString()
                        + "\nProsimy wejść w poniższy link by pobrać etykietę: " +
                        "\n" + "http://localhost:8080/api/parcels/toPDF/" + parcel.getId().toString()));
    }


    @Transactional(readOnly = true)
    public List<Parcel> findAll() {
        return parcelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parcel findById(UUID id) {
        if (parcelRepository.exists(Example.of(parcelRepository.findById(id).orElseThrow()))) {
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
        String headerValue = "attachment; filename=" + parcel.getId() + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToPdf(response, parcel);

    }

    public void exportParcelToCSVById(HttpServletResponse response, UUID id) throws DocumentException, IOException {
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
