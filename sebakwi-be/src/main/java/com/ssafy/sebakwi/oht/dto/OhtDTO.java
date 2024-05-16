package com.ssafy.sebakwi.oht.dto;

import com.ssafy.sebakwi.oht.domain.Oht;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OhtDTO {

    private int id;
    private String serialNumber;
    private boolean maintenance;

    public Oht toEntity() {
        return Oht.builder()
                .id(this.id)
                .serialNumber(this.serialNumber)
                .maintenance(this.maintenance)
                .build();
    }
}
