package com.warehouse.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy=AUTO)
    private Long departmentId;

    @JsonProperty("city")
    @NotBlank(message="City cannot be empty")
    private String city;

    @JsonProperty("departmentCode")
    @NotBlank(message="Deparment code cannot be empty")
    private String departmentCode;

    @OneToOne(fetch = LAZY)
    private Parcel parcel;


}
