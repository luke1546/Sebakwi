package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.WheelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CheckupListDetailModalResponse {

    private String wheelNumber;
    private int position;
    private String ohtNumber;
    private String checkedDate;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private String createdDate;

    @Builder
    public CheckupListDetailModalResponse(String wheelNumber, int position, String ohtNumber,
                                          LocalDateTime checkedDate, WheelStatus status,
                                          LocalDateTime createdDate) {
        this.wheelNumber = wheelNumber;
        this.position = position;
        this.ohtNumber = ohtNumber;
        this.checkedDate = formatCheckedDate(checkedDate);
        this.status = status;
        this.createdDate = formatCreatedDate(createdDate);
    }

    private String formatCheckedDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    private String formatCreatedDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }
}
