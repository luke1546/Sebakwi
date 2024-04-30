package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.CheckupList;
import com.ssafy.sebakwi.product.domain.Oht;
import com.ssafy.sebakwi.product.domain.Wheel;
import com.ssafy.sebakwi.product.domain.WheelStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDTO {

//    private int id;
    private Wheel wheel;
    private LocalDateTime checkedDate;
    private String wheelImage;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private float diameter;
    private boolean crack;
    private boolean stamp;
    private boolean peeling;

    public CheckupList toEntity() {
        return CheckupList.builder()
//                .id(id)
                .wheel(wheel)
                .checkedDate(checkedDate)
                .wheelImage(wheelImage)
                .status(status)
                .diameter(diameter)
                .crack(crack)
                .stamp(stamp)
                .peeling(peeling)
                .build();
    }
}
