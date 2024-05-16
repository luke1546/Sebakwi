package com.ssafy.sebakwi.checkupList.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CheckupListPageResponse<T> {

    private Long totalCount;
    private int totalPages;
    private List<T> checkupListArray;

    public CheckupListPageResponse(Long totalCount, int totalPages, List<T> checkupListArray) {
        this.totalCount = totalCount;
        this.totalPages = totalPages;
        this.checkupListArray = checkupListArray;
    }
}
