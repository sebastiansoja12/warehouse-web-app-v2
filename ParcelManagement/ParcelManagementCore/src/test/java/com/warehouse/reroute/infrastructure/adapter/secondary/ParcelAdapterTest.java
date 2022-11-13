package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.reroute.domain.enumeration.ParcelType;
import com.warehouse.reroute.domain.model.Parcel;
import com.warehouse.reroute.domain.model.RerouteToken;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import com.warehouse.reroute.domain.vo.Recipient;
import com.warehouse.reroute.domain.vo.Sender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParcelAdapterTest {

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private RerouteTokenRepository rerouteTokenRepository;

    @InjectMocks
    private ParcelAdapter adapter;


    @Test
    void shouldUpdate() {
        // given
        final Long parcelId = 123456L;
        final UpdateParcelRequest request = UpdateParcelRequest.builder()
                .parcel(Parcel.builder()
                        .recipient(Recipient.builder().build())
                        .sender(Sender.builder().build())
                        .parcelType(ParcelType.AVERAGE).build())
                .id(parcelId)
                .token(12345)
                .build();
        final Token token = Token.builder()
                .value(12345).build();
        final RerouteToken rerouteToken = new RerouteToken();
        when(rerouteTokenRepository.findByToken(token)).thenReturn(rerouteToken);
        when(parcelRepository.update(request)).thenReturn(Optional.ofNullable(ParcelResponse.builder().build()));
        // when
        final ParcelResponse parcelResponse = adapter.update(request);

        // then
        verify(parcelRepository).update(request);
        assertThat(parcelResponse.getRecipient()).isEqualTo(null);
    }
}
