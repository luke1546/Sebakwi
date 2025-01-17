package com.ssafy.sebakwi.checkupList.service;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.checkupList.domain.CheckupListRepository;
import com.ssafy.sebakwi.checkupList.dto.*;
import com.ssafy.sebakwi.oht.domain.Oht;
import com.ssafy.sebakwi.oht.domain.OhtRepository;
import com.ssafy.sebakwi.oht.dto.OhtDTO;
import com.ssafy.sebakwi.util.SimpleRestClient;
import com.ssafy.sebakwi.util.exception.CustomException;
import com.ssafy.sebakwi.util.exception.CustomExceptionStatus;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import com.ssafy.sebakwi.wheel.domain.WheelRepository;
import com.ssafy.sebakwi.wheel.domain.WheelStatus;
import com.ssafy.sebakwi.wheel.dto.CreateWheelRequest;
import com.ssafy.sebakwi.wheel.dto.CreateWheelResponse;
import com.ssafy.sebakwi.wheel.dto.WheelDto;
import com.ssafy.sebakwi.wheel.service.WheelService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ssafy.sebakwi.checkupList.dto.CheckupListDetailModalWheelNumberList.*;

@Slf4j
@RequiredArgsConstructor
@Service
@ToString
public class CheckupListService {

    private final OhtRepository ohtRepository;
    private final CheckupListRepository checkupListRepository;
    private final WheelRepository wheelRepository;

    private final WheelService wheelService;
    private final SimpleRestClient simpleRestClient;

    /**
     * 젯슨나노로부터 바퀴 검사데이터 받기
     */

    public CreateWheelResponse getCreateWheelResponse(CreateWheelRequest request) {

        WheelStatus wheelStatus = WheelStatus.NORMAL;
        if ( request.isCrack() || request.isStamp() || request.getDiameter() >= 1) {
            wheelStatus = WheelStatus.ABNORMAL;
        }

        Wheel checkedWheel = wheelRepository.findByWheelSerialNumber(request.getWheelSerialNumber());

        CheckupList checkupList = CheckupList.builder()
                .wheel(checkedWheel)
                .checkedDate(LocalDateTime.now())
                .wheelImage(request.getWheelImage())
                .status(wheelStatus)
                .diameter(request.getDiameter())
                .crack(request.isCrack())
                .stamp(request.isStamp())
                .abrasion(request.getDiameter() >= 1)
                .build();

        checkupListRepository.save(checkupList);

        if (wheelStatus == WheelStatus.ABNORMAL) {

            wheelService.updateMonthlyStatus(convertToCheckupListDto(checkupList));
            String message = String.format(
                    "# :rotating_light: %s 휠 (%s) 크랙 확인  \\n",
                    checkupList.getWheel().getSerialNumber(),
                    checkupList.getWheel().getOht().getSerialNumber()
            );

            StringBuilder additionalMessages = new StringBuilder();
            if (checkupList.getDiameter() >= 1 || checkupList.isCrack() || checkupList.isStamp() || checkupList.isAbrasion()) {
                additionalMessages.append("\n# ");

                if (checkupList.isCrack()) {
                    additionalMessages.append("크랙 발생! ");
                }
                if (checkupList.isStamp()) {
                    additionalMessages.append("스탬프 발생! ");
                }
                if (checkupList.isAbrasion()) {
                    additionalMessages.append("초과 마모 발생! ");
                }
            }

            additionalMessages.append("\n");
            additionalMessages.append("# [검사 이미지 바로가기]");
            additionalMessages.append("(" + request.getWheelImage() + ")");

            message += additionalMessages.toString().trim();

            simpleRestClient.notifyWebhook(message);
        }

        return new CreateWheelResponse(request.getWheelSerialNumber());
    }

    private CheckupListDto convertToCheckupListDto(CheckupList checkupList) {

        WheelDto wheelDto = WheelDto.toDto(checkupList.getWheel());

        return CheckupListDto.builder()
                .id(checkupList.getId())
                .wheel(wheelDto)
                .checkedDate(checkupList.getCheckedDate())
                .wheelImage(checkupList.getWheelImage())
                .status(checkupList.getStatus())
                .diameter(checkupList.getDiameter())
                .crack(checkupList.isCrack())
                .stamp(checkupList.isStamp())
                .abrasion(checkupList.isAbrasion())
                .build();
    }



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



    /**
     * checkupListDetail 모달
     */

    public List<CheckupListDetailModalWheel> getCheckupListDetailModalWheels(Long checkupListId) {

        CheckupList findCheckupList = checkupListRepository.findById(checkupListId)
                .orElseThrow(RuntimeException::new);

        return getCheckupListDetailModalResponse(findCheckupList);
    }



    public List<CheckupListDetailModalWheel> getCheckupListDetailModalResponse(CheckupList findCheckupList) {


        // oht와 찾는 시간대
        String ohtNumber = findCheckupList.getWheel().getOht().getSerialNumber();
        LocalDateTime findDateTime = findCheckupList.getCheckedDate();

        List<CheckupListDetailModalWheel> response = checkupListDetailWheelInfo(ohtNumber, findDateTime);
        return response;

    }


    /**
     * checkupList 목록 찾기
     */

    public CheckupListPageResponse<CheckupListArrayResponse> getCheckupListArray(CheckupListArrayRequest request) {

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

            if (request.isOnlyAbnormal()) {

                if (request.getStartDateTime() == null) {
                    Page<CheckupList> page = checkupListRepository.findStatusCheckupListArray(
                            request.isCheckedDate(), endDateTime,
                            WheelStatus.ABNORMAL, pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
                    if (page.isEmpty()) {
                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                    }
                    Long totalCount = page.getTotalElements();
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalCount, totalPages, responseList);

                } else {

                    Page<CheckupList> page = checkupListRepository.findStartDateTimeStatusCheckupListArray(
                            request.isCheckedDate(), startDateTime,
                            endDateTime, WheelStatus.ABNORMAL,
                            pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
                    if (page.isEmpty()) {
                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                    }
                    Long totalCount = page.getTotalElements();
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalCount, totalPages, responseList);

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
                    Long totalCount = page.getTotalElements();
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalCount, totalPages, responseList);

                } else {

                    Page<CheckupList> page = checkupListRepository.findStartDateTimeCheckupListArray(
                            request.isCheckedDate(), startDateTime,
                            endDateTime, pageable, request.isSortByCheck(),
                            request.isDesc(), request.getPosition(), request.getOhtSerialNumber(), request.getWheelSerialNumber());
                    if (page.isEmpty()) {
                        throw new CustomException(CustomExceptionStatus.CHECKUPLIST_NOT_FOUND);
                    }
                    Long totalCount = page.getTotalElements();
                    int totalPages = page.getTotalPages();
                    List<CheckupList> checkupLists = page.getContent();
                    List<CheckupListArrayResponse> responseList = CheckupListMapper.toDtoList(checkupLists);
                    return new CheckupListPageResponse<>(totalCount, totalPages, responseList);
                }
            }
    }


    public static class CheckupListMapper {

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
     * CheckupListDetailModalWheel 만들기
     */

    public List<CheckupListDetailModalWheel> checkupListDetailWheelInfo(String ohtNumber, LocalDateTime findDateTime) {

        LocalDateTime findStartDateTime = findDateTime.minusSeconds(2);
        LocalDateTime findEndDateTime = findDateTime.plusSeconds(2);

        List<CheckupListDetailModalDto> wheelAndCheckupListList = wheelRepository.findOtherWheelDetailByWheelNumber(ohtNumber, findEndDateTime);
        log.info("wheelAndCheckupListList.size()={}", wheelAndCheckupListList.size());

        List<CheckupListDetailModalWheel> response = wheelAndCheckupListList.stream().map(o -> CheckupListDetailModalWheel.builder()
                        .checkupListId(o.getCheckupList().getId())
                        .wheelNumber(o.getWheel().getSerialNumber())
                        .position(o.getWheel().getPosition())
                        .ohtNumber(o.getWheel().getOht().getSerialNumber())
                        .checkedDate(o.getCheckupList().getCheckedDate())
                        .wheelImage(o.getCheckupList().getWheelImage())
                        .diameter(o.getCheckupList().getDiameter())
                        .crack(o.getCheckupList().isCrack())
                        .stamp(o.getCheckupList().isStamp())
                        .abrasion(o.getCheckupList().isAbrasion())
                        .status(o.getCheckupList().getStatus())
                        .createdDate(o.getWheel().getCreatedDate())
                        .build())
                .collect(Collectors.toList());
        log.info("response.size()={}", response.size());
        return response;

    }

}
