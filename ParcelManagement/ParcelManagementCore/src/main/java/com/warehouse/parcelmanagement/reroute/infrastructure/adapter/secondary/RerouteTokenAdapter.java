package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.mail.domain.service.MailService;
import com.warehouse.mail.domain.vo.Notification;
import com.warehouse.parcelmanagement.reroute.domain.exception.ParcelNotFoundException;
import com.warehouse.parcelmanagement.reroute.domain.model.*;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteNotification;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RequestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RerouteTokenAdapter implements RerouteTokenPort {

    private final RequestMapper requestMapper;

    private final RerouteTokenRepository rerouteTokenRepository;

    private final ParcelRepository parcelRepository;

    private final MailService mailService;

    void buildReroutingInformation(RerouteRequest rerouteRequest, RerouteResponse rerouteResponse) {
        final Notification notification = requestMapper.map(buildNotification(rerouteRequest, rerouteResponse));
        mailService.sendNotification(notification);
    }

    @Override
    public ParcelResponse update(UpdateParcelRequest request) {
        return parcelRepository.update(request);
    }

    @Override
    public ParcelResponse loadByParcelId(Long parcelId) {
        return parcelRepository.loadByParcelId(parcelId).orElseThrow(
                () -> new ParcelNotFoundException("Parcel was not found"));
    }

    @Override
    public RerouteTokenResponse findByToken(Token token) {
        return rerouteTokenRepository.findByToken(token).orElseThrow(
                () -> new RerouteTokenNotFoundException("Reroute token was not found"));
    }


    @Override
    public RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId parcelId) {
        return rerouteTokenRepository.loadByTokenAndParcelId(token, parcelId).stream().findFirst().orElseThrow(
                () -> new RerouteTokenNotFoundException("Reroute token was not found"));
    }

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
        final RerouteResponse rerouteResponse = rerouteTokenRepository.saveReroutingToken(rerouteRequest.getParcelId());
        buildReroutingInformation(rerouteRequest, rerouteResponse);
        return rerouteResponse;
    }

    public RerouteNotification buildNotification(RerouteRequest rerouteRequest, RerouteResponse rerouteResponse) {
        return RerouteNotification.builder()
                .body("Prosimy wejść w link: " + "http:localhost:4200/reroute-edit/" + rerouteRequest.getParcelId()
                        + "/" + rerouteResponse.getToken())
                .recipient(rerouteRequest.getEmail())
                .subject("Zmiana danych przesyłki: " + rerouteResponse.getParcelId())
                .build();
    }
}
