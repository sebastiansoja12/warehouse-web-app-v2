package com.warehouse.mail.domain.port.secondary;

import com.warehouse.mail.domain.vo.Notification;

public interface MailPort {

    void sendNotification(Notification notification);
}
