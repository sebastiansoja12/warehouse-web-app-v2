package com.warehouse.parcelmanagement.parcel.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parcel")
@Entity(name = "Parcel")
public class ParcelEntity {

    @Id
    @Column(unique = true, updatable = false, insertable = false)
    private Long id;

}
