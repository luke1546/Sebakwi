package com.ssafy.sebakwi.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wheel {

    @Id @GeneratedValue
    @Column(name = "wheel_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oht_id")
    private Oht oht;

    @Column(length = 20, name = "serial_number")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private WheelStatus currentStatus;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(length = 20)
    private int position;

    @Builder
    public Wheel(Oht oht, String serialNumber, WheelStatus currentStatus, LocalDateTime createdDate, int position) {
        this.oht = oht;
        this.serialNumber = serialNumber;
        this.currentStatus = currentStatus;
        this.createdDate = createdDate;
        this.position = position;
    }

}
