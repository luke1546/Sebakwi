package com.ssafy.sebakwi.checkupList.service;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.checkupList.domain.CheckupListRepository;
import com.ssafy.sebakwi.checkupList.dto.*;
import com.ssafy.sebakwi.oht.domain.Oht;
import com.ssafy.sebakwi.oht.domain.OhtRepository;
import com.ssafy.sebakwi.oht.dto.OhtDTO;
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

    /**
     * 젯슨나노로부터 바퀴 검사데이터 받기
     */

    public CreateWheelResponse getCreateWheelResponse(CreateWheelRequest request) {

        WheelStatus wheelStatus = WheelStatus.NORMAL;
        if (request.isCrack() || request.isStamp() || request.isPeeling()) {
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
                .peeling(request.isPeeling())
                .build();

        checkupListRepository.save(checkupList);

        // 바퀴의 현재상태 업데이트
//        if (checkedWheel.getCurrentStatus() != wheelStatus) {
//            wheelService.updateWheelCurrentStatus(checkedWheel.getSerialNumber(), wheelStatus);
//        }

        if (wheelStatus == WheelStatus.ABNORMAL) {

            wheelService.updateMonthlyStatus(convertToCheckupListDto(checkupList));
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
                .peeling(checkupList.isPeeling())
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

    public List<CheckupListDetailModalWheel> getCheckupListDetailModalWheels(int checkupListId) {

        CheckupList findCheckupList = checkupListRepository.findById(checkupListId)
                .orElseThrow(RuntimeException::new);

        return getCheckupListDetailModalResponse(findCheckupList);
    }



    public List<CheckupListDetailModalWheel> getCheckupListDetailModalResponse(CheckupList findCheckupList) {

        //찾는 바퀴

//        OhtDTO ohtDTO = OhtDTO.builder()
//                .id(findCheckupList.getWheel().getOht().getId())
//                .serialNumber(findCheckupList.getWheel().getOht().getSerialNumber())
//                .maintenance(findCheckupList.getWheel().getOht().isMaintenance())
//                .build();
//
//        WheelDto wheelDto = WheelDto.builder()
//                .oht(ohtDTO)
//                .serialNumber(findCheckupList.getWheel().getSerialNumber())
////                .currentStatus(findCheckupList.getWheel().getCurrentStatus())
//                .createdDate(findCheckupList.getWheel().getCreatedDate())
//                .position(findCheckupList.getWheel().getPosition())
//                .build();
//
//        CheckupListModalDto checkupListDTO = CheckupListModalDto.builder()
//                .checkupListId(findCheckupList.getId())
//                .wheel(wheelDTO)
//                .checkedDate(findCheckupList.getCheckedDate())
//                .wheelImage(findCheckupList.getWheelImage())
//                .status(findCheckupList.getStatus())
//                .diameter(findCheckupList.getDiameter())
//                .crack(findCheckupList.isCrack())
//                .stamp(findCheckupList.isStamp())
//                .peeling(findCheckupList.isPeeling())
//                .build();

        // oht와 찾는 시간대
        String ohtNumber = findCheckupList.getWheel().getOht().getSerialNumber();
        LocalDateTime findDateTime = findCheckupList.getCheckedDate();

        List<CheckupListDetailModalWheel> response = checkupListDetailWheelInfo(ohtNumber, findDateTime);
        return response;


//        CheckupListDetailModalWheel modalResponse = CheckupListDetailModalWheel.builder()
//                .checkupListId(checkupListDTO.getCheckupListId())
//                .wheelNumber(checkupListDTO.getWheel().getSerialNumber())
//                .position(checkupListDTO.getWheel().getPosition())
//                .ohtNumber(checkupListDTO.getWheel().getOht().getSerialNumber())
//                .checkedDate(checkupListDTO.getCheckedDate())
//                .wheelImage(checkupListDTO.getWheelImage())
//                .diameter(checkupListDTO.getDiameter())
//                .crack(checkupListDTO.isCrack())
//                .stamp(checkupListDTO.isStamp())
//                .peeling(checkupListDTO.isPeeling())
//                .status(checkupListDTO.getStatus())
//                .createdDate(checkupListDTO.getWheel().getCreatedDate())
//                .build();
//
////        CheckupListDetailModalWheelNumberList wheelNumberList = constructWheelNumberList(checkupListDTO.getWheel().getOht().getSerialNumber(), checkupListDTO.getCheckedDate());
//
//        return CheckupListDetailModalResponse.builder()
//                .checkupListDetailModalWheel(modalResponse)
//                .build();
    }




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
     * CheckupListDetailModalWheelNumberList 만들기
     */

//    public CheckupListDetailModalWheelNumberList constructWheelNumberList(String ohtNumber, LocalDateTime checkedDateTime) {
//        List<Wheel> wheelNumberList = wheelRepository.findWheelByOhtNumber(ohtNumber);
//        return builder()
//                .FL(new WheelNumberStatus(wheelNumberList.get(0).getSerialNumber(), wheelNumberList.get(0).getCurrentStatus()))
//                .FR(new WheelNumberStatus(wheelNumberList.get(1).getSerialNumber(), wheelNumberList.get(1).getCurrentStatus()))
//                .RL(new WheelNumberStatus(wheelNumberList.get(2).getSerialNumber(), wheelNumberList.get(2).getCurrentStatus()))
//                .RR(new WheelNumberStatus(wheelNumberList.get(3).getSerialNumber(), wheelNumberList.get(3).getCurrentStatus()))
//                .build();
//    }


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
                        .peeling(o.getCheckupList().isPeeling())
                        .status(o.getCheckupList().getStatus())
                        .createdDate(o.getWheel().getCreatedDate())
                        .build())
                .collect(Collectors.toList());
        log.info("response.size()={}", response.size());
        return response;

    }

}
