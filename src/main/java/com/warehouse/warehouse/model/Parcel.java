package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Parcel {

    @Id
    @GeneratedValue()
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;


    private String firstName;

    private String lastName;

    private String sender_telephone;

    private String destination_telephone;

    private String destination_address;

    private String email;

    private boolean isCustom;





}
