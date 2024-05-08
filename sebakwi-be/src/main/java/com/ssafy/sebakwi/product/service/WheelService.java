package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.domain.WheelRepository;
import com.ssafy.sebakwi.product.dto.CheckupListDTO;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatus;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatusCount;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WheelService {

    private final WheelRepository wheelRepository;
    private final MainService mainService;

    /**
     * 메인페이지 관련
     */

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

        defaultMonthlyStatus = response;

        return response;
    }

    public void updateMonthlyStatus(CheckupListDTO checkupListDTO) {

        if (defaultMonthlyStatus == null) {
            defaultMonthlyStatus = monthlyWheelStatusInfo();
        }

        int crack = 0;
        int stamp = 0;
        int peeling = 0;

        defaultMonthlyStatus.updateWheelList(defaultMonthlyStatus.getWheelList().stream().filter(o -> {
            if (o.getWheelNumber() == checkupListDTO.getWheel().getSerialNumber()) {
                if (o.isCrack()) {
                    defaultMonthlyStatus.getCount().updateCrack(-1);
                }
                if (o.isStamp()) {
                    defaultMonthlyStatus.getCount().updateStamp(-1);
                }
                if (o.isPeeling()) {
                    defaultMonthlyStatus.getCount().updatePeeling(-1);
                }
                return false;
            }
            return true;
        })
                .collect(Collectors.toList()));

        if (checkupListDTO.isCrack()) {
            defaultMonthlyStatus.getCount().updateCrack(1);
        }
        if (checkupListDTO.isStamp()) {
            defaultMonthlyStatus.getCount().updateStamp(1);
        }
        if (checkupListDTO.isPeeling()) {
            defaultMonthlyStatus.getCount().updatePeeling(1);
        }

        WheelMonthlyStatus newMonthlyStatus = WheelMonthlyStatus.builder()
                .wheelNumber(checkupListDTO.getWheelImage())
                .ohtNumber(checkupListDTO.getWheel().getOht().getSerialNumber())
                .position(checkupListDTO.getWheel().getPosition())
                .crack(checkupListDTO.isCrack())
                .stamp(checkupListDTO.isStamp())
                .peeling(checkupListDTO.isPeeling())
                .build();

        defaultMonthlyStatus.getWheelList().add(newMonthlyStatus) ;

        mainService.sendMonthly(1L, defaultMonthlyStatus);

    }

}
