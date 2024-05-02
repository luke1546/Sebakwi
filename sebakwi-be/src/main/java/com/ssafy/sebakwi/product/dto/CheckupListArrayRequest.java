package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.WheelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Getter
public class CheckupListArrayRequest {

    private boolean isCheckedDate;

    private LocalDate startDateTime;

    private LocalDate endDateTime;

    private boolean onlyAbnormal;

    private int position;

    private String ohtSerialNumber;

    private int page;

    private boolean sortByCheck;

    private boolean desc;

    @Builder
    public CheckupListArrayRequest(boolean isCheckedDate, LocalDate startDateTime, LocalDate endDateTime,
                                    boolean onlyAbnormal, int position, String ohtSerialNumber, int page, boolean sortByCheck, boolean desc) {

        this.isCheckedDate = isCheckedDate;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.onlyAbnormal = onlyAbnormal;
        this.position = position;
        this.ohtSerialNumber = ohtSerialNumber;
        this.page = page;
        this.sortByCheck = sortByCheck;
        this.desc = desc;

    }


    public void noEndDateTime() {
        this.endDateTime = LocalDate.now();
    }
}
