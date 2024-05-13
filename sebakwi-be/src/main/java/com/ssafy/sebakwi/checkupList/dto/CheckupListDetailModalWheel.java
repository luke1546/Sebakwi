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
public class CheckupListDetailModalWheel {

    private int checkupListId;
    private String wheelNumber;
    private int position;
    private String ohtNumber;
    private String checkedDate;

    private String wheelImage;
    private float diameter;
    private boolean crack;
    private boolean stamp;
    private boolean peeling;

    @Enumerated(EnumType.STRING)
    private WheelStatus status;

    private LocalDate createdDate;

    @Builder
    public CheckupListDetailModalWheel(int checkupListId, String wheelNumber, int position, String ohtNumber,
                                          LocalDateTime checkedDate, String wheelImage, float diameter,
                                          boolean crack, boolean stamp, boolean peeling,
                                          WheelStatus status, LocalDate createdDate) {
        this.checkupListId = checkupListId;
        this.wheelNumber = wheelNumber;
        this.position = position;
        this.ohtNumber = ohtNumber;
        this.checkedDate = formatCheckedDate(checkedDate);
        this.wheelImage = wheelImage;
        this.diameter = diameter;
        this.crack = crack;
        this.stamp = stamp;
        this.peeling = peeling;
        this.status = status;
//        this.createdDate = formatCreatedDate(createdDate);
        this.createdDate = createdDate;
    }

    private String formatCheckedDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
