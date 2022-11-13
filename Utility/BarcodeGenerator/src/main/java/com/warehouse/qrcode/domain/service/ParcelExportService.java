package com.warehouse.qrcode.domain.service;

import com.warehouse.shipment.domain.model.Parcel;

import javax.servlet.http.HttpServletResponse;

public interface ParcelExportService {

    void exportToPdf(HttpServletResponse response, Parcel parcel) throws Exception;
}
