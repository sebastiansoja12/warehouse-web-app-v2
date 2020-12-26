package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parcels")
@AllArgsConstructor
public class ParcelController {

    private final ParcelService parcelService;

    @PostMapping
    public Parcel addPaczka(@RequestBody Parcel parcel){
        return parcelService.save(parcel);
    }
    @GetMapping("/all")
    public List<Parcel> getAll(){
        return parcelService.findAll();
    }
    @GetMapping("/{id}")
    public Parcel getParcelByParcelId(@PathVariable UUID id){
        return parcelService.findById(id);
    }


}
