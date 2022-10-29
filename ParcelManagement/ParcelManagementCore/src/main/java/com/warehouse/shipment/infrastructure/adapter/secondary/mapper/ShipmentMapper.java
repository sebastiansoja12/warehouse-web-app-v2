package com.warehouse.shipment.infrastructure.adapter.secondary.mapper;

import com.warehouse.paypal.domain.model.PaymentResponse;
import com.warehouse.shipment.domain.model.Parcel;
import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.domain.model.ShipmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShipmentMapper {

    @Mapping(source = "parcel.sender.firstName", target = "sender.firstName")
    @Mapping(source = "parcel.sender.lastName", target = "sender.lastName")
    @Mapping(source = "parcel.sender.email", target = "sender.email")
    @Mapping(source = "parcel.sender.telephoneNumber", target = "sender.telephoneNumber")
    @Mapping(source = "parcel.sender.city", target = "sender.city")
    @Mapping(source = "parcel.sender.postalCode", target = "sender.postalCode")
    @Mapping(source = "parcel.sender.street", target = "sender.street")
    @Mapping(source = "parcel.recipient.firstName", target = "recipient.firstName")
    @Mapping(source = "parcel.recipient.lastName", target = "recipient.lastName")
    @Mapping(source = "parcel.recipient.email", target = "recipient.email")
    @Mapping(source = "parcel.recipient.telephoneNumber", target = "recipient.telephoneNumber")
    @Mapping(source = "parcel.recipient.city", target = "recipient.city")
    @Mapping(source = "parcel.recipient.postalCode", target = "recipient.postalCode")
    @Mapping(source = "parcel.recipient.street", target = "recipient.street")
    @Mapping(source = "parcel.parcelType", target = "parcelType")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", ignore = true)
    Parcel map(ShipmentRequest request);

    @Mapping(source = "parcelId", target = "parcelId")
    @Mapping(source = "paymentResponse.link.paymentUrl", target = "paymentUrl")
    ShipmentResponse map(Long parcelId, PaymentResponse paymentResponse);
}
