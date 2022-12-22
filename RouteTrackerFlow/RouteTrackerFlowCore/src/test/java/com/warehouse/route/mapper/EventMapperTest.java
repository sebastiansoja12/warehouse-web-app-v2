package com.warehouse.route.mapper;


import com.warehouse.route.domain.vo.SupplyInformation;
import com.warehouse.route.infrastructure.adapter.primary.mapper.EventMapper;
import com.warehouse.route.infrastructure.adapter.primary.mapper.EventMapperImpl;
import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EventMapperTest {

    private EventMapper eventMapper;

    @BeforeEach
    void setup() {
        eventMapper = new EventMapperImpl();
    }

    @Test
    void shouldMapSupplyInformationDtoToSupplyInformation() {
        // given
        final SupplyInformationDto supplyInformationDto = new SupplyInformationDto();
        supplyInformationDto.setParcelId(1L);
        // when
        final SupplyInformation supplyInformation = eventMapper.map(supplyInformationDto);
        // then
        assertThat(supplyInformation.getParcelId()).isEqualTo(supplyInformationDto.getParcelId());
    }
}
