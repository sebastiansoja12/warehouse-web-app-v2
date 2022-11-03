package com.warehouse.barcode.infrastructure.adapter.secondary.mapper;

import com.warehouse.barcode.domain.model.Parcel;
import com.warehouse.shipment.infrastructure.adapter.entity.ParcelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelEntityMapper {

    @Mapping(target = "sender.firstName", source = "firstName")
    @Mapping(target = "sender.lastName", source = "lastName")
    @Mapping(target = "sender.email", source = "senderEmail")
    @Mapping(target = "sender.telephoneNumber", source = "senderTelephone")
    @Mapping(target = "sender.city", source = "senderCity")
    @Mapping(target = "sender.postalCode", source = "senderPostalCode")
    @Mapping(target = "sender.street", source = "senderStreet")
    @Mapping(target = "recipient.firstName", source = "recipientFirstName")
    @Mapping(target = "recipient.lastName", source = "recipientLastName")
    @Mapping(target = "recipient.email", source = "recipientEmail")
    @Mapping(target = "recipient.telephoneNumber", source = "recipientTelephone")
    @Mapping(target = "recipient.city", source = "recipientCity")
    @Mapping(target = "recipient.postalCode", source = "recipientPostalCode")
    @Mapping(target = "recipient.street", source = "recipientStreet")
    @Mapping(target = "parcelType", source = "parcelType")
    @Mapping(target = "id", source = "id")
    Parcel map(ParcelEntity parcel);
}
