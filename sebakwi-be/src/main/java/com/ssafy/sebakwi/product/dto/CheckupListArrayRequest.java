package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.WheelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Getter
public class CheckupListArrayRequest {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private boolean onlyAbnormal;

    private int page;

    @Builder
    public CheckupListArrayRequest(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                    boolean onlyAbnormal, int page) {

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.onlyAbnormal = onlyAbnormal;
        this.page = page;
    }


    public void noEndDateTime() {
        this.endDateTime = LocalDateTime.now();
    }
}
