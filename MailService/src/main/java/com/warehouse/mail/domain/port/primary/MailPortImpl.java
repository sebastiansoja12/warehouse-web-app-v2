package com.warehouse.mail.domain.port.primary;

import com.warehouse.mail.domain.service.MailService;
import com.warehouse.mail.domain.vo.Notification;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MailPortImpl implements MailPort {

    private final MailService mailService;

    @Override
    public void sendNotification(Notification notification) {
        mailService.sendNotification(notification);
    }
}
