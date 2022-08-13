package com.warehouse.service;

import com.warehouse.dto.ParcelDto;
import com.warehouse.entity.Parcel;
import com.warehouse.entity.ParcelNotification;
import com.warehouse.entity.RerouteToken;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RerouteTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RerouteService {

    private final RerouteTokenRepository rerouteTokenRepository;

    private final ParcelRepository parcelRepository;

    private final MailService mailService;


    public String sendReroutingInformationAndGenerateToken(UUID parcelId, String email) {
        final Parcel parcel = parcelRepository
                .findByIdAndSenderEmail(parcelId, email)
                .orElseThrow(() -> new ParcelNotFound("Paczka nie zostala znaleziona"));

        mailService.sendNotification(new ParcelNotification
                ("Została przez Państwa nadana przesyłka ",
                        email, "Docelowa destynacja paczki to: " +
                        parcel.getId() + ", "
                        + parcel.getRecipientTelephone()));

        return parcel.getRecipientTelephone();
    }

    public RerouteToken generateRerouteTokenWithGivenParcel(UUID id) {
        final Parcel parcelRerouteToken = parcelRepository
                .findById(id).orElseThrow(() -> new ParcelNotFound("Paczka nie zostala znaleziona"));
        return generateReroutingToken(parcelRerouteToken);
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
    }

    public RerouteToken generateReroutingToken(Parcel parcel) {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(rerouteToken.generateToken());
        rerouteToken.setCreatedDate(Instant.now());
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
}
