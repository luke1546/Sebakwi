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

//    private List<Wheel> wheel = new ArrayList<>();

//    @Builder
//    public OhtDTO(int id, String serialNumber, boolean repair) {
//        this.id = id;
//        this.serialNumber = serialNumber;
//        this.repair = repair;
//    }

    public Oht toEntity() {
        return Oht.builder()
                .id(this.id)
                .serialNumber(this.serialNumber)
                .maintenance(this.maintenance)
                .build();
    }
}
