package com.warehouse.service;

import com.warehouse.entity.Parcel;
import com.warehouse.entity.ParcelNotification;
import com.warehouse.entity.ReroutingToken;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.exceptions.WarehouseException;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.ReroutingTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReroutingService {

    private final ReroutingTokenRepository reroutingTokenRepository;

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

    public void updateParcelInformation(Parcel parcel, UUID id) {
        final Parcel parcelToUpdate = parcelRepository
                .findById(id).orElseThrow(() -> new ParcelNotFound("Paczka nie zostala znaleziona"));
        parcelToUpdate.setFirstName(parcel.getFirstName());
        parcelToUpdate.setLastName(parcel.getLastName());
        parcelToUpdate.setSenderTelephone(parcel.getSenderTelephone());
        parcelToUpdate.setSenderEmail(parcel.getSenderEmail());
        parcelToUpdate.setRecipientFirstName(parcel.getRecipientFirstName());
        parcelToUpdate.setRecipientLastName(parcel.getRecipientLastName());
        parcelToUpdate.setRecipientEmail(parcel.getRecipientEmail());
        parcelToUpdate.setRecipientCity(parcel.getRecipientCity());
        parcelToUpdate.setRecipientPostalCode(parcel.getRecipientPostalCode());
        parcelToUpdate.setRecipientStreet(parcel.getRecipientStreet());

        parcelRepository.save(parcelToUpdate);
    }


    public ReroutingToken generateReroutingToken() {
        final ReroutingToken reroutingToken = new ReroutingToken();
        reroutingToken.setToken(reroutingToken.generateToken());
        reroutingToken.setCreatedDate(Instant.now());
        return reroutingToken;
    }

    void validateReroutingToken(Integer token) {
        reroutingTokenRepository.findByToken(token)
                .orElseThrow(() -> new WarehouseException("Invalid rerouting Token"));
    }

    public void deleteReroutingToken(Integer token) {
        reroutingTokenRepository.deleteByToken(token);
    }
}
