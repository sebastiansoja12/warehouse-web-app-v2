package com.warehouse.mapper;

import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierMapperTest {

    private SupplierMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SupplierMapperImpl();
    }

    @Test
    void givenSupplierDtoToSupplier() {
        // GIVEN
        final SupplierDto dto = new SupplierDto();
        dto.setFirstName("Test");
        dto.setLastName("Test");
        dto.setSupplierCode("TT1");
        dto.setTelephone("123");

        // WHEN
        final Supplier entity = mapper.mapToDto(dto);

        // THEN
        // AND: Entity is not null
        assertNotNull(entity);
        // AND: First names are equal
        assertEquals(entity.getFirstName(), dto.getFirstName());
        // AND: Depot codes are equal
        assertEquals(entity.getDepot().getDepotCode(), dto.getDepotCode());

    }

}
