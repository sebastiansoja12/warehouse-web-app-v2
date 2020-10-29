package com.warehouse.warehouse.service;

import com.warehouse.warehouse.data.PaczkaDataAccessService;
import com.warehouse.warehouse.model.Paczka;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.PaczkaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class PaczkaService {

private final PaczkaRepository paczkaRepository;
private final PaczkaDataAccessService paczkaDataAccessService;

    @Transactional
    public Paczka save(Paczka paczka){
        paczka.setCreatedAt(Instant.now());
        return paczkaRepository.save(paczka);
    }

    @Transactional(readOnly = true)
    public List<Paczka> findAll(){
        return paczkaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Paczka findByKodPaczki(String kodPaczki) {
        return paczkaRepository.findByKodPaczki(kodPaczki).orElse(null);
    }


    @Transactional
    public void update(Paczka paczka, String kod) {

    }
}
