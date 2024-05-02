package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.domain.CheckupList;
import com.ssafy.sebakwi.product.domain.CheckupListRepository;
import com.ssafy.sebakwi.product.domain.WheelStatus;
import com.ssafy.sebakwi.product.dto.CheckupListArrayRequest;
import com.ssafy.sebakwi.product.dto.CheckupListArrayResponse;
import com.ssafy.sebakwi.product.dto.CheckupListPageResponse;
import com.ssafy.sebakwi.util.exception.CustomException;
import com.ssafy.sebakwi.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CheckupListService {

    private final CheckupListRepository checkupListRepository;

    public CheckupListPageResponse<CheckupListArrayResponse> findCheckupListArray(CheckupListArrayRequest request) {

        if (request.getEndDateTime() == null) {
            request.noEndDateTime();
        }

        // pageable 객체 생성
        int size = 20;
        Pageable pageable = PageRequest.of(request.getPage(), size);

        if (request.isOnlyAbnormal()) {

            if (request.getStartDateTime() == null) {

                Page<CheckupList> page = checkupListRepository.findStatusCheckupListArray(request.getEndDateTime(), WheelStatus.ABNORMAL, pageable);
                if (page.isEmpty()) {
                    throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                }
                int totalPages = page.getTotalPages();
                List<CheckupList> checkupLists = page.getContent();
                List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                return new CheckupListPageResponse<>(totalPages, responseList);

            } else {

                Page<CheckupList> page = checkupListRepository.findStartDateTimeStatusCheckupListArray(request.getStartDateTime(), request.getEndDateTime(), WheelStatus.ABNORMAL, pageable);
                if (page.isEmpty()) {
                    throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                }
                int totalPages = page.getTotalPages();
                List<CheckupList> checkupLists = page.getContent();
                List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                return new CheckupListPageResponse<>(totalPages, responseList);

            }
        } else {
            if (request.getStartDateTime() == null) {

                Page<CheckupList> page = checkupListRepository.findCheckupListArray(request.getEndDateTime(), pageable);
                if (page.isEmpty()) {
                    throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                }
                int totalPages = page.getTotalPages();
                List<CheckupList> checkupLists = page.getContent();
                List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                return new CheckupListPageResponse<>(totalPages, responseList);

            } else {

                Page<CheckupList> page = checkupListRepository.findStartDateTimeCheckupListArray(request.getStartDateTime(), request.getEndDateTime(), pageable);
                if (page.isEmpty()) {
                    throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                }
                int totalPages = page.getTotalPages();
                List<CheckupList> checkupLists = page.getContent();
                List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                return new CheckupListPageResponse<>(totalPages, responseList);
            }
        }

    }

    public class CheckupListMapper {

        public static List<CheckupListArrayResponse> toDtoList(List<CheckupList> checkupLists) {
            return checkupLists.stream()
                    .map(CheckupListMapper::toDto)
                    .collect(Collectors.toList());
        }

        public static CheckupListArrayResponse toDto(CheckupList checkupList) {
            return CheckupListArrayResponse.builder()
                    .checkupListId(checkupList.getId())
                    .wheelNumber(checkupList.getWheel().getSerialNumber())
                    .position(checkupList.getWheel().getPosition())
                    .ohtNumber(checkupList.getWheel().getOht().getSerialNumber())
                    .checkedDate(checkupList.getCheckedDate())
                    .status(checkupList.getStatus())
                    .createdDate(checkupList.getWheel().getCreatedDate())
                    .build();
        }
    }
}
