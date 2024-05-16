package com.ssafy.sebakwi.checkupList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
