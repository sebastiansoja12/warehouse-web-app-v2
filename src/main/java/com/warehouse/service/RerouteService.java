package com.warehouse.service;

import com.warehouse.dto.ParcelDto;
import com.warehouse.dto.RerouteRequest;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.ParcelNotification;
import com.warehouse.entity.RerouteToken;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RerouteService {

    private final RerouteTokenRepository rerouteTokenRepository;

    private final ParcelRepository parcelRepository;

    private final MailService mailService;

    private final static Long SECONDS_TO_EXPIRE = 600L;

    public void sendReroutingInformation(RerouteRequest rerouteRequest) {
        final Parcel parcel = parcelRepository
                .findByIdAndSenderEmail(rerouteRequest.getParcelId(), rerouteRequest.getEmail())
                .orElseThrow(() -> new ParcelNotFound("Paczka nie zostala znaleziona"));

        final RerouteToken rerouteToken = generateRerouteTokenWithGivenParcel(parcel);
        rerouteTokenRepository.save(rerouteToken);


        mailService.sendNotification(new ParcelNotification
                ("Edycja danych przesyłki  " + parcel.getId(),
                        rerouteRequest.getEmail(),
                        "By edytować dane przesyłki prosimy udać się na stronę do przekierowywania " +
                        "przesyłek i podać niżej wygenerowany token " +
                        "\n" + rerouteToken.getToken()));
    }

    public RerouteToken generateRerouteTokenWithGivenParcel(Parcel parcel) {
        return generateReroutingToken(parcel);
    }

    public void updateParcel(ParcelDto parcel, UUID parcelId, Integer token) {
        validateReroutingToken(token);
        final Parcel parcelRerouteToken = parcelRepository
                .findById(parcelId).orElseThrow(() -> new ParcelNotFound("Paczka nie zostala znaleziona"));
        parcelRerouteToken.setFirstName(parcel.getFirstName());
        parcelRerouteToken.setLastName(parcel.getLastName());
        parcelRerouteToken.setSenderTelephone(parcel.getSenderTelephone());
        parcelRerouteToken.setSenderEmail(parcel.getSenderEmail());
        parcelRerouteToken.setRecipientFirstName(parcel.getRecipientFirstName());
        parcelRerouteToken.setRecipientLastName(parcel.getRecipientLastName());
        parcelRerouteToken.setRecipientEmail(parcel.getRecipientEmail());
        parcelRerouteToken.setRecipientCity(parcel.getRecipientCity());
        parcelRerouteToken.setRecipientPostalCode(parcel.getRecipientPostalCode());
        parcelRerouteToken.setRecipientStreet(parcel.getRecipientStreet());

        parcelRepository.save(parcelRerouteToken);

        rerouteTokenRepository.deleteByToken(token);
    }

    public RerouteToken generateReroutingToken(Parcel parcel) {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(rerouteToken.generateToken());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));
        rerouteToken.setParcel(parcel);
        return rerouteToken;
    }

    void validateReroutingToken(Integer token) {
        rerouteTokenRepository.findByToken(token)
                .orElseThrow(() -> new WarehouseException("Invalid rerouting Token"));
    }


    public void deleteReroutingToken(Integer token) {
        rerouteTokenRepository.deleteByToken(token);
    }

    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpired() {
        final Date now = Date.from(Instant.now());
        rerouteTokenRepository.deleteAllExpiredSince(now);
    }
}
