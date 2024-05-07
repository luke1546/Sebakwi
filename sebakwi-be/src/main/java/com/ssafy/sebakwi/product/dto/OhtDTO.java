package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.Oht;
import com.ssafy.sebakwi.product.domain.Wheel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
