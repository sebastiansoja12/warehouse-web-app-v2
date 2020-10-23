package com.warehouse.warehouse.dao;

import com.warehouse.warehouse.model.Paczka;

import java.util.UUID;

public interface PaczkaDao {

    int insertPaczka(UUID id, boolean isCustom, Paczka paczka);

    default int insertPaczka(Paczka paczka){
        UUID id=UUID.randomUUID();
        boolean isCustom=false;
        return insertPaczka(id,isCustom,paczka);
    }
}
