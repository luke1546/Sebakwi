package com.ssafy.sebakwi.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oht {

    @Id @GeneratedValue
    @Column(name = "oht_id")
    private int id;

    @Column(name = "serial_number", length = 20)
    private String serialNumber;

    private boolean repair;

    @OneToMany(mappedBy = "oht", cascade = CascadeType.ALL)
    private List<Wheel> wheel = new ArrayList<>();

    @Builder
    public Oht(String serialNumber, boolean repair) {
        this.serialNumber = serialNumber;
        this.repair = repair;
    }
}