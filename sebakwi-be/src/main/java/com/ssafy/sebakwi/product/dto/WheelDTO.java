package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.Oht;
import com.ssafy.sebakwi.product.domain.Wheel;
import com.ssafy.sebakwi.product.domain.WheelStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelDTO {

//    private int id;
    private OhtDTO oht;
    private String serialNumber;
    private WheelStatus currentStatus;
    private LocalDate createdDate;
    private int position;



    public Wheel toEntity() {

        Oht ohtEntity = oht.toEntity();
        return Wheel.builder()
                .oht(ohtEntity)
                .serialNumber(serialNumber)
                .currentStatus(currentStatus)
                .createdDate(createdDate)
                .position(position)
                .build();
    }

    public static WheelDTO toDTO(Wheel wheel) {
        OhtDTO ohtDTO = OhtDTO.builder()
                .id(wheel.getOht().getId())
                .serialNumber(wheel.getOht().getSerialNumber())
                .maintenance(wheel.getOht().isMaintenance())
                .build();

        return WheelDTO.builder()
                .oht(ohtDTO)
                .serialNumber(wheel.getSerialNumber())
                .currentStatus(wheel.getCurrentStatus())
                .createdDate(wheel.getCreatedDate())
                .position(wheel.getPosition())
                .build();
    }

}
