package com.ssafy.sebakwi.checkupList.dto;

import com.ssafy.sebakwi.wheel.domain.WheelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CheckupListArrayResponse {

    private Long checkupListId;
    private String wheelNumber;
    private int position;
    private String ohtNumber;
    private String checkedDate;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private LocalDate createdDate;

    @Builder
    public CheckupListArrayResponse(Long checkupListId, String wheelNumber, int position,
                                    String ohtNumber, LocalDateTime checkedDate, WheelStatus status,
                                    LocalDate createdDate) {
        this.wheelNumber = wheelNumber;
        this.position = position;
        this.ohtNumber = ohtNumber;
        this.checkedDate = formatCheckedDate(checkedDate);
        this.status = status;
        this.createdDate = createdDate;
        this.checkupListId = checkupListId;
    }

    private String formatCheckedDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
