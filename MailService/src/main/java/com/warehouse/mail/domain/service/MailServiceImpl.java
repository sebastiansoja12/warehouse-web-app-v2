package com.warehouse.mail.domain.service;

import com.warehouse.mail.domain.port.secondary.MailPort;
import com.warehouse.mail.domain.vo.Notification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailPort mailPort;

    @Override
    public void sendNotification(Notification notification) {
        mailPort.sendNotification(notification);
    }
}
