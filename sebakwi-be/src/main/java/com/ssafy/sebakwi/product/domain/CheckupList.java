package com.ssafy.sebakwi.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkup_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckupList {

    @Id @GeneratedValue
    @Column(name = "checkup_list_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wheel_id")
    private Wheel wheel;

    @Column(name = "checked_date")
    private LocalDateTime checkedDate;

    @Column(name = "wheel_image")
    private String wheelImage;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private float diameter;
    private boolean crack;
    private boolean stamp;
    private boolean peeling;

    @Builder
    public CheckupList(Wheel wheel, String wheelImage, WheelStatus status, float diameter, boolean crack, boolean stamp, boolean peeling) {
        this.wheel = wheel;
        this.wheelImage = wheelImage;
        this.status = status;
        this.diameter = diameter;
        this.crack = crack;
        this.stamp = stamp;
        this.peeling = peeling;
    }

}
