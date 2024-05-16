package com.ssafy.sebakwi.wheel.dto;

import com.ssafy.sebakwi.oht.domain.Oht;
import com.ssafy.sebakwi.oht.dto.OhtDTO;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelDto {

//    private int id;
    private OhtDTO oht;
    private String serialNumber;
//    private WheelStatus currentStatus;
    private LocalDate createdDate;
    private int position;



    public Wheel toEntity() {

        Oht ohtEntity = oht.toEntity();
        return Wheel.builder()
                .oht(ohtEntity)
                .serialNumber(serialNumber)
//                .currentStatus(currentStatus)
                .createdDate(createdDate)
                .position(position)
                .build();
    }

    public static WheelDto toDto(Wheel wheel) {
        OhtDTO ohtDTO = OhtDTO.builder()
                .id(wheel.getOht().getId())
                .serialNumber(wheel.getOht().getSerialNumber())
                .maintenance(wheel.getOht().isMaintenance())
                .build();

        return WheelDto.builder()
                .oht(ohtDTO)
                .serialNumber(wheel.getSerialNumber())
//                .currentStatus(wheel.getCurrentStatus())
                .createdDate(wheel.getCreatedDate())
                .position(wheel.getPosition())
                .build();
    }

}
