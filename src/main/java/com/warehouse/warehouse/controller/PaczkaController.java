package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.model.Paczka;
import com.warehouse.warehouse.service.PaczkaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class PaczkaController {

    private final PaczkaService paczkaService;

    @PostMapping
    public Paczka addPaczka(@RequestBody Paczka paczka){
        return paczkaService.save(paczka);
    }
    @GetMapping("/all")
    public List<Paczka> getAll(){
        return paczkaService.findAll();
    }
    @GetMapping("/{kodPaczki}")
    public Paczka getPaczkaByKodPaczki(@PathVariable String kodPaczki){
        return paczkaService.findByKodPaczki(kodPaczki);
    }
    @PutMapping("/{kodPaczki}")
    public void updatePaczkaByKodPaczki(@RequestBody Paczka paczka, @PathVariable String kodPaczki){
         paczkaService.update(paczka, kodPaczki);
    }
}
