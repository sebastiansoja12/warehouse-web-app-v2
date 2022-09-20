package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.EmailDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.ParcelId;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteRequestDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class PrimaryRequestMapperTest {

    //private final PrimaryRequestMapper mapper;

    private final static Long PARCEL_ID = 100001L;

    private final static String EMAIL = "sebastian5152@wp.pl";

    @Test
    void shouldMapFromRerouteRequestDtoToRerouteRequest() {
        // given
        final RerouteRequestDto requestDto = new RerouteRequestDto();
        requestDto.setParcelId(parcelId());
        requestDto.setEmail(email());
        // when
        /*final RerouteRequest request = mapper.map(requestDto);
        // then
        assertThat(request).isNotNull();
        assertThat(request.getParcelId()).isEqualTo(PARCEL_ID);
        assertThat(request.getEmail()).isEqualTo(EMAIL);

         */
    }

    @Test
    void shouldMapFromUpdateParcelRequestDtoToUpdateParcelRequest() {

    }

    @Test
    void shouldMapFromParcelDtoToParcel() {

    }

    @Test
    void shouldMapFromTokenDtoToToken() {

    }

    @Test
    void shouldMapFromParcelIdDtoToParcelId() {

    }

    private ParcelId parcelId() {
        final ParcelId parcelId = new ParcelId();
        parcelId.setValue(PARCEL_ID);
        return parcelId;
    }

    private EmailDto email() {
        final EmailDto email = new EmailDto();
        email.setValue(EMAIL);
        return email;
    }
}
