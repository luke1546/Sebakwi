package com.ssafy.sebakwi.checkupList.dto;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.wheel.domain.WheelStatus;
import com.ssafy.sebakwi.wheel.dto.WheelDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListModalDto {

    private int checkupListId;
    private WheelDTO wheel;
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
//                .id(checkupListId)
                .wheel(wheel.toEntity())
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
