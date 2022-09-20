package com.warehouse.mail.infrastructure.adapter.primary;


import com.warehouse.mail.domain.port.primary.MailPort;
import com.warehouse.mail.domain.vo.Notification;
import serviceDto.MailService;
import serviceDto.NotificationDto;
import serviceDto.mapper.RequestMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailServiceAdapter implements MailService {

    private final MailPort mailPort;

    private final RequestMapper requestMapper;

    @Override
    public void sendNotification(NotificationDto notificationDto) {
        final Notification notification = requestMapper.map(notificationDto);
        mailPort.sendNotification(notification);
    }
}
