package com.warehouse.entity;

import com.warehouse.enumeration.ParcelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Parcel_ID")
    @TableGenerator(name = "Parcel_ID", initialValue = 1000000, allocationSize = 100)
    private Long id;

    @Valid
    @NotBlank
    private String firstName;

    @Valid
    @NotBlank
    private String lastName;

    @Valid
    @NotBlank
    private String senderTelephone;

    @Valid
    @NotBlank
    private String senderEmail;

    @Valid
    @NotBlank
    private String recipientEmail;

    @Valid
    @NotBlank
    private String recipientTelephone;

    @Valid
    @NotBlank
    private String recipientFirstName;

    @Valid
    @NotBlank
    private String recipientLastName;

    @Valid
    @NotBlank
    private String recipientCity;

    @Valid
    @NotBlank
    private String recipientStreet;

    @Valid
    @NotBlank
    @Pattern(regexp="\\d{2}-\\d{3}")
    private String recipientPostalCode;

    private ParcelType parcelType;

    private double price;

}
