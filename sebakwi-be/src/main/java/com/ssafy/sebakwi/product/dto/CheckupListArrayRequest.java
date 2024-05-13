package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.WheelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class CheckupListArrayRequest {

    private boolean isCheckedDate;

    private LocalDate startDateTime;

    private LocalDate endDateTime;

    private boolean onlyAbnormal;

    private int position;

    private String ohtSerialNumber;

    private String wheelSerialNumber;

    private int page;

    private boolean sortByCheck;

    private boolean desc;


    public void noEndDateTime() {
        this.endDateTime = LocalDate.now();
    }
}
