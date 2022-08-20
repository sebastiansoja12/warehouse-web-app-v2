package com.warehouse.service;

import com.warehouse.dto.ParcelDto;
import com.warehouse.dto.RerouteRequest;
import com.warehouse.dto.TokenValidationRequest;
import com.warehouse.dto.UpdateParcelRequest;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.ParcelNotification;
import com.warehouse.entity.RerouteToken;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class RerouteService {

    private final RerouteTokenRepository rerouteTokenRepository;

    private final ParcelRepository parcelRepository;

    private final MailService mailService;

    private final static Long SECONDS_TO_EXPIRE = 600L;

    public RerouteToken sendReroutingInformation(RerouteRequest rerouteRequest) {
        final Parcel parcel = parcelRepository
                .findByIdAndSenderEmail(UUID.fromString(rerouteRequest.getParcelId()), rerouteRequest.getEmail())
                .orElseThrow(() -> new ParcelNotFound("Paczka nie zostala znaleziona"));

        final RerouteToken rerouteToken = generateRerouteTokenWithGivenParcel(rerouteRequest);
        log.info("Token " + rerouteToken.getToken() + " saved");

        mailService.sendNotification(new ParcelNotification
                ("Edycja danych przesyłki  " + parcel.getId(),
                        rerouteRequest.getEmail(),
                        "By edytować dane przesyłki prosimy udać się na stronę do przekierowywania " +
                        "przesyłek i podać niżej wygenerowany token " +
                        "\n" + rerouteToken.getToken()));
        return rerouteToken;
    }

    public RerouteToken generateRerouteTokenWithGivenParcel(RerouteRequest rerouteRequest) {
        return generateReroutingToken(rerouteRequest);
    }

    @Transactional
    public Parcel updateParcel(UpdateParcelRequest parcelRequest) {
        validateReroutingToken(parcelRequest.getToken());
        rerouteTokenRepository.deleteByToken(parcelRequest.getToken());
        final Parcel parcelToUpdate = validateParcel(parcelRequest.getId());
        parcelToUpdate.setFirstName(parcelRequest.getParcel().getFirstName());
        parcelToUpdate.setLastName(parcelRequest.getParcel().getLastName());
        parcelToUpdate.setSenderTelephone(parcelRequest.getParcel().getSenderTelephone());
        parcelToUpdate.setSenderEmail(parcelRequest.getParcel().getSenderEmail());
        parcelToUpdate.setRecipientFirstName(parcelRequest.getParcel().getRecipientFirstName());
        parcelToUpdate.setRecipientLastName(parcelRequest.getParcel().getRecipientLastName());
        parcelToUpdate.setRecipientEmail(parcelRequest.getParcel().getRecipientEmail());
        parcelToUpdate.setRecipientCity(parcelRequest.getParcel().getRecipientCity());
        parcelToUpdate.setRecipientPostalCode(parcelRequest.getParcel().getRecipientPostalCode());
        parcelToUpdate.setRecipientStreet(parcelRequest.getParcel().getRecipientStreet());
        return parcelRepository.save(parcelToUpdate);
    }

    private Parcel validateParcel(UUID parcelId) {
        return parcelRepository
                .findById(parcelId).orElseThrow(() -> new ParcelNotFound("Parcel was not found"));
    }

    public boolean tokenValidation(TokenValidationRequest tokenValidation) {
        final Optional<RerouteToken> rerouteToken = rerouteTokenRepository
                .findByParcelIdAndToken(tokenValidation.getParcelId(), tokenValidation.getToken())
                .stream().max(Comparator.comparing(RerouteToken::getCreatedDate));

        return rerouteToken.get().getExpiryDate().isAfter(Instant.now());
    }

    public RerouteToken generateReroutingToken(RerouteRequest rerouteRequest) {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(SECONDS_TO_EXPIRE));
        rerouteToken.setParcelId(rerouteRequest.getParcelId());
        return rerouteTokenRepository.save(rerouteToken);
    }

    RerouteToken validateReroutingToken(Integer token) {
       return rerouteTokenRepository.findByToken(token)
                .orElseThrow(() -> new WarehouseException("Invalid rerouting Token"));
    }

    public void deleteReroutingToken(Integer token) {
        rerouteTokenRepository.deleteByToken(token);
    }

    @Scheduled(cron = "${purge.cron.expression}")
    @Transactional
    public void purgeExpired() {
        rerouteTokenRepository.deleteAllExpiredSince(Instant.now());
    }
}
