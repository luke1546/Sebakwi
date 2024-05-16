package com.ssafy.sebakwi.checkupList.dto;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.wheel.domain.WheelStatus;
import com.ssafy.sebakwi.wheel.dto.WheelDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListModalDto {

    private Long checkupListId;
    private WheelDto wheel;
    private LocalDateTime checkedDate;
    private String wheelImage;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private double diameter;
    private boolean crack;
    private boolean stamp;
    private boolean abrasion;

    public CheckupList toEntity() {
        return CheckupList.builder()
                .wheel(wheel.toEntity())
                .checkedDate(checkedDate)
                .wheelImage(wheelImage)
                .status(status)
                .diameter(diameter)
                .crack(crack)
                .stamp(stamp)
                .abrasion(abrasion)
                .build();
    }
}
