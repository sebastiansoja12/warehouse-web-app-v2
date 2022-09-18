package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.ParcelNotification;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.exception.WarehouseMailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailCreator {

    private final JavaMailSender mailSender;

    @Autowired
    public MailCreator(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    void sendNotification(ParcelNotification parcelNotification) {
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("inparcel@inp.com");
            messageHelper.setTo(parcelNotification.getRecipient());
            messageHelper.setSubject(parcelNotification.getSubject());
            messageHelper.setText(parcelNotification.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Notification has been sent");
        } catch (MailException e) {
            log.error("Error", e);
            throw new WarehouseMailException("Error  " + parcelNotification.getRecipient(), e);
        }
    }
}
