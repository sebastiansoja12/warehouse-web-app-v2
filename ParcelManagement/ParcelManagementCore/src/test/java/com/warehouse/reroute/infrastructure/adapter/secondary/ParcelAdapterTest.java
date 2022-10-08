package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.reroute.domain.enumeration.ParcelType;
import com.warehouse.reroute.domain.model.Parcel;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.reroute.domain.vo.ParcelId;
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

    @InjectMocks
    private ParcelAdapter adapter;

    @Test
    void shouldLoadByParcelId() {
        // given
        final Long parcelId = 123456L;
        final ParcelResponse parcelResponse = ParcelResponse.builder()
                .parcelId(new ParcelId(parcelId))
                .parcelType(ParcelType.AVERAGE)
                .recipient(Recipient.builder().build())
                .sender(Sender.builder().build())
                .build();
        when(parcelRepository.loadByParcelId(parcelId)).thenReturn(parcelResponse);
        // when
        final ParcelResponse loadedParcelResponse = adapter.loadByParcelId(parcelId);
        // then
        verify(parcelRepository).loadByParcelId(parcelId);
        assertThat(parcelResponse.getParcelId()).isEqualTo(loadedParcelResponse.getParcelId());
    }

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
        when(parcelRepository.update(request)).thenReturn(Optional.ofNullable(ParcelResponse.builder().build()));
        // when
        final ParcelResponse parcelResponse = adapter.update(request);

        // then
        verify(parcelRepository).update(request);
        assertThat(parcelResponse.getRecipient()).isEqualTo(null);
    }
}
