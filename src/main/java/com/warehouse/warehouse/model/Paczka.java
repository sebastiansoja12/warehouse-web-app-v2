package com.warehouse.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Paczka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;

    private boolean isCustom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

}
