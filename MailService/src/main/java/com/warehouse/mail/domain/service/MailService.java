package com.warehouse.mail.domain.service;

import com.warehouse.mail.domain.vo.Notification;

public interface MailService {

    void sendNotification(Notification notification);
}
