package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.domain.CheckupList;
import com.ssafy.sebakwi.product.domain.CheckupListRepository;
import com.ssafy.sebakwi.product.domain.WheelStatus;
import com.ssafy.sebakwi.product.dto.*;
import com.ssafy.sebakwi.util.exception.CustomException;
import com.ssafy.sebakwi.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CheckupListService {

    private final CheckupListRepository checkupListRepository;

    /**
     * 체크업리스트 requestParam 받기
     */

    public CheckupListArrayRequest getCheckupListArrayRequest(boolean isCheckedDate, String startDateTimeStr, String endDateTimeStr, boolean onlyAbnormal, int position, String ohtSerialNumber, int page, boolean sortByCheck, boolean desc) {

        LocalDate startDateTime = null;
        LocalDate endDateTime = null;

        if (startDateTimeStr != null && !startDateTimeStr.isEmpty()) {
            startDateTime = LocalDate.parse(startDateTimeStr);
        }

        if (endDateTimeStr != null && !endDateTimeStr.isEmpty()) {
            endDateTime = LocalDate.parse(endDateTimeStr);
        }

        return new CheckupListArrayRequest(
                isCheckedDate, startDateTime, endDateTime, onlyAbnormal, position,
                ohtSerialNumber, page, sortByCheck, desc
        );
    }


    public CheckupListPageResponse<CheckupListArrayResponse> findCheckupListArray(CheckupListArrayRequest request) {

        if (request.getEndDateTime() == null) {
            request.noEndDateTime();
        }

        // 시작시간, 끝시간 LocalDate 타입으로 변환
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = request.getEndDateTime().atTime(LocalTime.MAX);

        if (request.getStartDateTime() != null) {
            startDateTime = request.getStartDateTime().atStartOfDay();
        }

        // pageable 객체 생성
        int size = 20;
        Pageable pageable = PageRequest.of(request.getPage(), size);

//        if (request.isCheckedDate()){
            if (request.isOnlyAbnormal()) {

                if (request.getStartDateTime() == null) {
                    Page<CheckupList> page = checkupListRepository.findStatusCheckupListArray(
                            request.isCheckedDate(), endDateTime,
                            WheelStatus.ABNORMAL, pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber());
                    if (page.isEmpty()) {
                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                    }
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalPages, responseList);

                } else {

                    Page<CheckupList> page = checkupListRepository.findStartDateTimeStatusCheckupListArray(
                            request.isCheckedDate(), startDateTime,
                            endDateTime, WheelStatus.ABNORMAL,
                            pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber());
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

                    Page<CheckupList> page = checkupListRepository.findCheckupListArray(
                            request.isCheckedDate(), endDateTime,
                            pageable, request.isSortByCheck(), request.isDesc(),
                            request.getPosition(), request.getOhtSerialNumber());
                    if (page.isEmpty()) {
                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                    }
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalPages, responseList);

                } else {

                    Page<CheckupList> page = checkupListRepository.findStartDateTimeCheckupListArray(
                            request.isCheckedDate(), startDateTime,
                            endDateTime, pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber());
                    if (page.isEmpty()) {
                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                    }
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalPages, responseList);
                }
            }
//        } else {
//
//            // 교체일시
//            if (request.isOnlyAbnormal()) {
//
//                if (request.getStartDateTime() == null) {
//
//                    Page<CheckupList> page = checkupListRepository.findCreatedStatusCheckupListArray(request.getEndDateTime(), WheelStatus.ABNORMAL, pageable, request.isSortByCheck(), request.isDesc());
//                    if (page.isEmpty()) {
//                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
//                    }
//                    int totalPages = page.getTotalPages();
//                    List<CheckupList> checkupLists = page.getContent();
//                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
//                    return new CheckupListPageResponse<>(totalPages, responseList);
//
//                } else {
//
//                    Page<CheckupList> page = checkupListRepository.findCreatedStartDateTimeStatusCheckupListArray(request.getStartDateTime(), request.getEndDateTime(), WheelStatus.ABNORMAL, pageable, request.isSortByCheck(), request.isDesc());
//                    if (page.isEmpty()) {
//                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
//                    }
//                    int totalPages = page.getTotalPages();
//                    List<CheckupList> checkupLists = page.getContent();
//                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
//                    return new CheckupListPageResponse<>(totalPages, responseList);
//
//                }
//            } else {
//                if (request.getStartDateTime() == null) {
//
//                    Page<CheckupList> page = checkupListRepository.findCreatedCheckupListArray(request.getEndDateTime(), pageable, request.isSortByCheck(), request.isDesc());
//                    if (page.isEmpty()) {
//                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
//                    }
//                    int totalPages = page.getTotalPages();
//                    List<CheckupList> checkupLists = page.getContent();
//                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
//                    return new CheckupListPageResponse<>(totalPages, responseList);
//
//                } else {
//
//                    Page<CheckupList> page = checkupListRepository.findCreatedStartDateTimeCheckupListArray(request.getStartDateTime(), request.getEndDateTime(), pageable, request.isSortByCheck(), request.isDesc());
//                    if (page.isEmpty()) {
//                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
//                    }
//                    int totalPages = page.getTotalPages();
//                    List<CheckupList> checkupLists = page.getContent();
//                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
//                    return new CheckupListPageResponse<>(totalPages, responseList);
//                }
//            }
//        }

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
