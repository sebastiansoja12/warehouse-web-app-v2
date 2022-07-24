package com.warehouse.service;

import com.warehouse.exceptions.WarehouseMailException;
import com.warehouse.entity.ParcelNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public
class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    void sendNotification(ParcelNotification parcelNotification) {
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("inparcel@inp.com");
            messageHelper.setTo(parcelNotification.getRecipient());
            messageHelper.setSubject(parcelNotification.getSubject());
            messageHelper.setText(parcelNotification.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Powiadomienie wysłane");
        } catch (MailException e) {
            log.error("Błąd", e);
            throw new WarehouseMailException("Blad  " + parcelNotification.getRecipient(), e);
        }
    }


}