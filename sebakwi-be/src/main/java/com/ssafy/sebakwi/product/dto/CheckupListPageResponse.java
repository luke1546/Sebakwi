package com.ssafy.sebakwi.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CheckupListPageResponse<T> {

    private int totalPages;
    private List<T> checkupListArray;

    public CheckupListPageResponse(int totalPages, List<T> checkupListArray) {
        this.totalPages = totalPages;
        this.checkupListArray = checkupListArray;
    }
}
