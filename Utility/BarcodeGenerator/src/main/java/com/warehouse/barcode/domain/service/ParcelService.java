package com.warehouse.barcode.domain.service;

import com.warehouse.shipment.infrastructure.adapter.entity.ParcelEntity;
import com.warehouse.shipment.infrastructure.adapter.secondary.ShipmentReadRepository;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@AllArgsConstructor
public class ParcelService {

    private final ShipmentReadRepository parcelReadRepository;

    private final ParcelExportService parcelExportService;

    public void exportParcelToPdfById(HttpServletResponse response, Long id) throws Exception {
        final ParcelEntity parcel = parcelReadRepository.findById(id).orElseThrow();
        response.setContentType("application/pdf");
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        final String currentDateTime = dateFormatter.format(new java.util.Date());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=" + parcel.getId() + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        parcelExportService.exportToPdf(response, parcel);

    }
}
