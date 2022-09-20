package com.warehouse.mail.infrastructure.adapter.secondary.mapper;

import com.warehouse.mail.domain.model.RerouteNotification;
import com.warehouse.mail.domain.vo.Notification;
import org.mapstruct.Mapper;

@Mapper
public interface MailMapper {


    RerouteNotification map(Notification notification);
}
