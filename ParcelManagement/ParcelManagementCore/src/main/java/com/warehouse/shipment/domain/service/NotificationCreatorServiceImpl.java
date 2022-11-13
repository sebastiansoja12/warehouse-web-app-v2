package com.warehouse.shipment.domain.service;

import com.warehouse.shipment.domain.model.ConstantBodyMailMessage;
import com.warehouse.shipment.domain.model.Parcel;
import com.warehouse.shipment.domain.vo.Notification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationCreatorServiceImpl implements NotificationCreatorService {

    private final static String SUBJECT = "Została nadana do państwa przesyłka: ";

    @Override
    public Notification createNotification(Parcel parcel, String paymentUrl) {
        final ConstantBodyMailMessage constantBodyMailMessage = new ConstantBodyMailMessage(parcel, paymentUrl);

        return Notification.builder()
                .body(constantBodyMailMessage.getMessage())
                .recipient(parcel.getRecipient().getEmail())
                .subject(SUBJECT)
                .build();
    }
}
