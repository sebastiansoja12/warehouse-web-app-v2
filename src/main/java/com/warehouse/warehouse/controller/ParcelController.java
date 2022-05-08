package com.warehouse.warehouse.controller;


import com.lowagie.text.DocumentException;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parcels")
@AllArgsConstructor
public class ParcelController {

    private final ParcelService parcelService;

    @PostMapping
    public String addParcel(@RequestBody Parcel parcel) throws Exception {
        return parcelService.save(parcel);
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
    public void exportPostByIdToCSV(HttpServletResponse reponse, @PathVariable UUID id) throws DocumentException,
            IOException {
        parcelService.exportParcelToCSVById(reponse, id);
    }

}
