package com.warehouse.warehouse.controller;


import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/parcels")
@AllArgsConstructor
public class ParcelController {

    private final ParcelService parcelService;

    @PostMapping
    public void addPaczka(@RequestBody Parcel parcel) throws Exception {
         parcelService.save(parcel);
    }

    @GetMapping("/all")
    public List<Parcel> getAll(){
        return parcelService.findAll();
    }

    @GetMapping("/{id}")
    public Parcel getParcelByParcelId(@PathVariable UUID id){
        return parcelService.findById(id);
    }

    @GetMapping("/toPDF/{id}")
    public void exportParcelByIdToPdf(HttpServletResponse reponse, @PathVariable UUID id) throws Exception {
        parcelService.exportParcelToPdfById(reponse, id);
    }

    @GetMapping("/csv/{id}")
    public void exportPostByIdToCSV(HttpServletResponse reponse, @PathVariable UUID id) throws DocumentException,IOException {
        parcelService.exportParcelToCSVById(reponse, id);
    }

}
