package com.warehouse.warehouse.controller;


import com.lowagie.text.DocumentException;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    @Autowired
    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public void addParcel(@RequestBody Parcel parcel) throws Exception {
        parcelService.save(parcel);
    }

    @GetMapping
    public List<Parcel> getAll() {
        return parcelService.findAll();
    }

    @GetMapping("/{id}")
    public Parcel getParcelByParcelId(@PathVariable UUID id) {
        return parcelService.findById(id);
    }

    @GetMapping("/{id}/label")
    public void exportParcelByIdToPdf(HttpServletResponse reponse, @PathVariable UUID id) throws Exception {
        parcelService.exportParcelToPdfById(reponse, id);
    }

    @GetMapping("/{id}/csv")
    public void exportPostByIdToCSV(HttpServletResponse reponse, @PathVariable UUID id) throws DocumentException, IOException {
        parcelService.exportParcelToCSVById(reponse, id);
    }

}
