package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.dto.PaczkaRequest;
import com.warehouse.warehouse.service.PaczkaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class PaczkaController {

    private final PaczkaService paczkaService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody PaczkaRequest paczkaRequest) {
        paczkaService.save(paczkaRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
