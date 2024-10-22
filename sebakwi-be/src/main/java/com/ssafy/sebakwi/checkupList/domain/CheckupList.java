package com.ssafy.sebakwi.checkupList.domain;

import com.ssafy.sebakwi.wheel.domain.WheelStatus;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkup_list")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CheckupList {

    @Id @GeneratedValue
    @Column(name = "checkup_list_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wheel_id")
    private Wheel wheel;

    @Column(name = "checked_date")
    private LocalDateTime checkedDate;

    @Column(name = "wheel_image")
    private String wheelImage;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private double diameter;
    private boolean crack;
    private boolean stamp;
    private boolean abrasion;

}
