package com.ssafy.sebakwi.oht.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Oht {

    @Id @GeneratedValue
    @Column(name = "oht_id")
    private int id;

    @Column(name = "serial_number", length = 20)
    private String serialNumber;

    private boolean maintenance;

}