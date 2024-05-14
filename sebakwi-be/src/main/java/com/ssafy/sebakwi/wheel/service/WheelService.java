package com.ssafy.sebakwi.wheel.service;

import com.ssafy.sebakwi.checkupList.dto.CheckupListDto;
import com.ssafy.sebakwi.sse.domain.EmitterRepository;
import com.ssafy.sebakwi.sse.service.SseService;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import com.ssafy.sebakwi.wheel.domain.WheelRepository;
import com.ssafy.sebakwi.util.exception.DuplicateDataException;
import com.ssafy.sebakwi.wheel.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
@EnableScheduling
public class WheelService {

    private final WheelRepository wheelRepository;
    private final SseService sseService;
    private final EmitterRepository emitterRepository;


    /**
     * 메인페이지 관련
     */

//    public void updateWheelCurrentStatus(String wheelSerialNumber, WheelStatus status) {
//        Wheel wheel = wheelRepository.findByWheelSerialNumber(wheelSerialNumber);
//        wheel.updateCurrentStatus(status);
//    }

    WheelMonthlyStatusResponse<WheelMonthlyStatus> defaultMonthlyStatus;

    public WheelMonthlyStatusResponse<WheelMonthlyStatus> monthlyWheelStatusInfo() {

        // 이번달 구하기
        LocalDate now = LocalDate.now();
        LocalDateTime startOfMonth = LocalDateTime.of(now.getYear(),now.getMonthValue(), 1, 0, 0, 0);

        List<Object[]> monthlyStatus = wheelRepository.findWheelMonthlyStatus(startOfMonth);

        List<WheelMonthlyStatus> collects = monthlyStatus.stream()
                        .map(arr -> WheelMonthlyStatus.builder()
                                .wheelNumber((String) arr[1])
                                .ohtNumber((String) arr[2])
                                .position((int) arr[3])
                                .crack((boolean) arr[4])
                                .stamp((boolean) arr[5])
                                .peeling((boolean) arr[6])
                                .build())
                .collect(Collectors.toList());

        int crack = 0;
        int stamp = 0;
        int peeling = 0;

        for (WheelMonthlyStatus collect : collects) {
            if (collect.isCrack()) {
                crack++;
            }
            if (collect.isStamp()) {
                stamp++;
            }
            if (collect.isPeeling()) {
                peeling++;
            }
        }
        int total = crack + stamp + peeling;

        WheelMonthlyStatusCount cnt = WheelMonthlyStatusCount.builder()
                .crack(crack)
                .stamp(stamp)
                .peeling(peeling)
                .total(total)
                .build();

        WheelMonthlyStatusResponse<WheelMonthlyStatus> response = WheelMonthlyStatusResponse.<WheelMonthlyStatus>builder()
                .count(cnt)
                .wheelList(collects)
                .build();

//        defaultMonthlyStatus = response;

        return response;
    }



    public void updateMonthlyStatus(CheckupListDto checkupListDto) {

        // 기존에 비정상 체크된 바퀴인데, 비정상 원인이 다를 경우 tmp에 저장
        List<WheelMonthlyStatus> tmp = new ArrayList<>();

        if (defaultMonthlyStatus == null) {

            defaultMonthlyStatus = monthlyWheelStatusInfo();
            tmpTooltips.add(defaultMonthlyStatus.getWheelList().get(0));

        } else {

            defaultMonthlyStatus.updateWheelList(defaultMonthlyStatus.getWheelList().stream().filter(o -> {
                        if (Objects.equals(o.getWheelNumber(), checkupListDto.getWheel().getSerialNumber())) {

                            if (Objects.equals(o.isCrack(), checkupListDto.isCrack()) && Objects.equals(o.isStamp(), checkupListDto.isStamp() && Objects.equals(o.isPeeling(), checkupListDto.isPeeling()))) {
                                throw new DuplicateDataException("This Wheel has no change");
                            }

                            // 기존에 있던거 카운트 뺌
                            if (o.isCrack()) {
                                defaultMonthlyStatus.getCount().updateCrack(-1);
                            }
                            if (o.isStamp()) {
                                defaultMonthlyStatus.getCount().updateStamp(-1);
                            }
                            if (o.isPeeling()) {
                                defaultMonthlyStatus.getCount().updatePeeling(-1);
                            }

                            // 새로 바뀐거 적용
                            WheelMonthlyStatus newMonthlyStatus = updateNewCheckupListDto(checkupListDto);
                            tmp.add(newMonthlyStatus);

                            // chart에 담을 데이터 추가
                            // 특정 시간 내에서 두 번 상태변화가 일어나면 한 바퀴가 두 번 추가되는 것을 방지하기 위해 여기서 처리
                            tmpTooltips.stream().filter(t -> {
                                if (Objects.equals(t.getWheelNumber(), newMonthlyStatus.getWheelNumber())) {
                                    return false;
                                }
                                tmpY--;
                                return true;
                            });

                            tmpTooltips.add(newMonthlyStatus);
                            return false;
                        }
                        return true;
                    })
                    .collect(Collectors.toList()));

            if (!tmp.isEmpty()) {
                tmp.forEach(o -> {
                    defaultMonthlyStatus.getWheelList().add(o);

                    // chart에 담을 데이터 추가
                    // 특정 시간 내에서 두번 상태변화가 일어나면 한 바퀴가 두 번 카운트되는데 어떻게 할지(배제할거면 여기다)
//                    tmpTooltips.add(o);

                });
            } else {

                // 새로 바뀐거 적용
                WheelMonthlyStatus newMonthlyStatus = updateNewCheckupListDto(checkupListDto);
                defaultMonthlyStatus.getWheelList().add(newMonthlyStatus);

                // cahrt에 추가
                tmpTooltips.add(newMonthlyStatus);
            }
        }
        tmpY++;

        // 이상감지시 데이터 업데이트
        wheelChartInfo();

        emitterRepository.getAllUuid().forEach(o ->
            sseService.sendMonthly(o, defaultMonthlyStatus)
        );

    }

    private WheelMonthlyStatus updateNewCheckupListDto(CheckupListDto checkupListDto) {

        if (checkupListDto.isCrack()) {
            defaultMonthlyStatus.getCount().updateCrack(1);
        }
        if (checkupListDto.isStamp()) {
            defaultMonthlyStatus.getCount().updateStamp(1);
        }
        if (checkupListDto.isPeeling()) {
            defaultMonthlyStatus.getCount().updatePeeling(1);
        }

        WheelMonthlyStatus newMonthlyStatus = WheelMonthlyStatus.builder()
                .wheelNumber(checkupListDto.getWheel().getSerialNumber())
                .ohtNumber(checkupListDto.getWheel().getOht().getSerialNumber())
                .position(checkupListDto.getWheel().getPosition())
                .crack(checkupListDto.isCrack())
                .stamp(checkupListDto.isStamp())
                .peeling(checkupListDto.isPeeling())
                .build();
        return newMonthlyStatus;
    }


    public List<WheelReplacementResponse> wheelReplacementInfo() {

        LocalDate today = LocalDate.now();
        LocalDate twoYearsAgo = today.minusYears(2);

        List<Wheel> wheelReplacement = wheelRepository.findWheelReplacement(twoYearsAgo);

        return wheelReplacement.stream().map(o -> WheelReplacementResponse.builder()
                        .wheelNumber(o.getSerialNumber())
                        .createdDate(o.getCreatedDate())
                        .build())
                .collect(Collectors.toList());

    }

    /**
     * chart에서 쓸 데이터 30초마다 갱신
     */

    // 특정 시간 내에 업데이트된 값을 저장하는 변수
    int tmpY= 0;
    List<WheelMonthlyStatus> tmpTooltips = new ArrayList<>();


    // 차트 데이터를 담을 변수
    private List<String> xData = new ArrayList<>();
    private List<Integer> yData = new ArrayList<>();
    private List<List<WheelMonthlyStatus>> toolTips = new ArrayList<>();

    @Scheduled(cron = "0,30 * * * * *")
    public void wheelChartInfo() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        // 한 차트에 라벨 20개
        if (xData.size() >= 20) {
            xData.remove(0);
            yData.remove(0);
            toolTips.remove(0);
        }
        xData.add(formattedNow);
        yData.add(tmpY);
        toolTips.add(new ArrayList<>(tmpTooltips));

        // tmp값들 초기화
        tmpY= 0;
        tmpTooltips = new ArrayList<>();

    }

    // 차트 데이터 보내기
    public WheelChartResponse sendChartInfo() {

        int seconds = LocalDateTime.now().getSecond();

        // 이상현황 발생시
//        if (seconds != 0 && seconds != 30) {
//            wheelChartInfo();
//        }

        WheelChartResponse response = WheelChartResponse.builder()
                .xData(xData)
                .yData(yData)
                .toolTips(toolTips)
                .build();

        return response;

    }

//    public void initializeWheelChartInfo() {
//        List<String> xData = new ArrayList<>();
//        List<Integer> yData = new ArrayList<>();;
//        List<List<WheelMonthlyStatus>> toolTips = new ArrayList<>();;
//    }
}
