package com.warehouse.warehouse.mapper;

import com.warehouse.warehouse.dto.ParcelDto;
import com.warehouse.warehouse.dto.ParcelTypeDto;
import com.warehouse.warehouse.model.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParcelMapperTest {

    private ParcelMapper mapper;

    @Test
    void givenParcelDtoToParcel() {

        // given
        ParcelDto dto = new ParcelDto();
        dto.setCustom(false);
        dto.setParcelType(ParcelTypeDto.AVERAGE);
        dto.setPrice(20);


        // when
        Parcel entity = mapper.map(dto);


        // then
        assertEquals(dto.getParcelType().name(), entity.getParcelType().name());

    }
}
