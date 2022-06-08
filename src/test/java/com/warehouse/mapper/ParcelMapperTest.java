package com.warehouse.mapper;

import com.warehouse.dto.ParcelDto;
import com.warehouse.dto.ParcelTypeDto;
import com.warehouse.model.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class ParcelMapperTest {

    private ParcelMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ParcelMapperImpl();
    }

    @Test
    void givenParcelDtoToParcel() {

        // given
        ParcelDto dto = new ParcelDto();
        dto.setFirstName("Test");
        dto.setLastName("Test");
        dto.setSenderEmail("test@test.test");
        dto.setSenderTelephone("123456789");
        dto.setRecipientFirstName("Test");
        dto.setRecipientLastName("Test");
        dto.setRecipientCity("Test");
        dto.setRecipientEmail("test@test.test");
        dto.setRecipientPostalCode("00-000");
        dto.setRecipientStreet("Test");
        dto.setRecipientTelephone("123456789");
        dto.setParcelType(ParcelTypeDto.AVERAGE);
        dto.setPrice(20);


        // when
        Parcel entity = mapper.map(dto);

        // then
        assertEquals(dto.getParcelType().name(), entity.getParcelType().name());
        assertEquals(dto.getFirstName(), entity.getFirstName());
        assertEquals(dto.getLastName(), entity.getLastName());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getSenderTelephone(), entity.getSenderTelephone());
    }
}
