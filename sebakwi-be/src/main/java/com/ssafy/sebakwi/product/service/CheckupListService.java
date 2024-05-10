package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.domain.*;
import com.ssafy.sebakwi.product.dto.*;
import com.ssafy.sebakwi.util.exception.CustomException;
import com.ssafy.sebakwi.util.exception.CustomExceptionStatus;
import jakarta.persistence.Tuple;
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

import static com.ssafy.sebakwi.product.dto.CheckupListDetailModalWheelNumberList.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CheckupListService {

    private final CheckupListRepository checkupListRepository;
    private final WheelRepository wheelRepository;

    /**
     * 체크업리스트 requestParam 받기
     */

    public CheckupListArrayRequest getCheckupListArrayRequest(boolean isCheckedDate, String startDateTimeStr, String endDateTimeStr, boolean onlyAbnormal, int position, String ohtSerialNumber, String wheelSerialNumber, int page, boolean sortByCheck, boolean desc) {

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
                ohtSerialNumber, wheelSerialNumber, page, sortByCheck, desc
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
        int size = 15;
        Pageable pageable = PageRequest.of(request.getPage(), size);

//        if (request.isCheckedDate()){
            if (request.isOnlyAbnormal()) {

                if (request.getStartDateTime() == null) {
                    Page<CheckupList> page = checkupListRepository.findStatusCheckupListArray(
                            request.isCheckedDate(), endDateTime,
                            WheelStatus.ABNORMAL, pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
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
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
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
                            request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
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
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
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

    /**
     * CheckupListDetailModalWheelNumberList 만들기
     */

    public CheckupListDetailModalWheelNumberList constructWheelNumberList(String ohtNumber) {
        List<Wheel> wheelNumberList = wheelRepository.findWheelByOhtNumber(ohtNumber);
        return builder()
                .FL(new WheelNumberStatus(wheelNumberList.get(0).getSerialNumber(), wheelNumberList.get(0).getCurrentStatus()))
                .FR(new WheelNumberStatus(wheelNumberList.get(1).getSerialNumber(), wheelNumberList.get(1).getCurrentStatus()))
                .RL(new WheelNumberStatus(wheelNumberList.get(2).getSerialNumber(), wheelNumberList.get(2).getCurrentStatus()))
                .RR(new WheelNumberStatus(wheelNumberList.get(3).getSerialNumber(), wheelNumberList.get(3).getCurrentStatus()))
                .build();
    }


    /**
     * CheckupListDetailModalWheel 만들기
     */

    public CheckupListDetailModalWheel checkupListDetailWheelInfo(String wheelNumber) {
        Tuple tuple = wheelRepository.findWheelDetailByWheelNumber(wheelNumber);
        Wheel wheel = tuple.get(0, Wheel.class);
        CheckupList checkupList = tuple.get(1, CheckupList.class);

        return CheckupListDetailModalWheel.builder()
                .wheelNumber(wheel.getSerialNumber())
                .position(wheel.getPosition())
                .ohtNumber(wheel.getOht().getSerialNumber())
                .checkedDate(checkupList.getCheckedDate())
                .wheelImage(checkupList.getWheelImage())
                .diameter(checkupList.getDiameter())
                .crack(checkupList.isCrack())
                .stamp(checkupList.isStamp())
                .peeling(checkupList.isPeeling())
                .status(checkupList.getStatus())
                .createdDate(wheel.getCreatedDate())
                .build();

    }

}
