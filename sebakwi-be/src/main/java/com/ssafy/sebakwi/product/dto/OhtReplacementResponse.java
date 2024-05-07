package com.ssafy.sebakwi.product.dto;

import lombok.Data;

@Data
public class OhtReplacementResponse {

    private int totalOht;
    private int maintenance;

    public OhtReplacementResponse(int totalOht, int maintenance) {
        this.totalOht = totalOht;
        this.maintenance = maintenance;
    }
}
