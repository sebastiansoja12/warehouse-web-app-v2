package com.warehouse.mapper;

import com.warehouse.dto.ParcelDto;
import com.warehouse.dto.ParcelTypeDto;
import com.warehouse.model.Parcel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParcelMapperTest {

    @Autowired
    private ParcelMapper mapper;

    @Test
    void givenParcelDtoToParcel() {

        // given
        ParcelDto dto = new ParcelDto();
        dto.setParcelType(ParcelTypeDto.AVERAGE);
        dto.setPrice(20);


        // when
        Parcel entity = mapper.map(dto);


        // then
        assertEquals(dto.getParcelType().name(), entity.getParcelType().name());

    }
}
