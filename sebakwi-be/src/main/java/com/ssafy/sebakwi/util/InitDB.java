package com.ssafy.sebakwi.util;

import com.ssafy.sebakwi.checkupList.controller.CheckupListController;
import com.ssafy.sebakwi.checkupList.service.CheckupListService;
import com.ssafy.sebakwi.jetson.domain.Jetson;
import com.ssafy.sebakwi.jetson.domain.JetsonRepository;
import com.ssafy.sebakwi.oht.domain.Oht;
import com.ssafy.sebakwi.oht.domain.OhtRepository;
import com.ssafy.sebakwi.wheel.controller.WheelController;
import com.ssafy.sebakwi.wheel.dto.CreateWheelRequest;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import com.ssafy.sebakwi.wheel.domain.WheelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

    private final OhtRepository ohtRepository;
    private final WheelRepository wheelRepository;
    private final JetsonRepository jetsonRepository;

    public void dbInit() {
        Optional<Oht> oo = ohtRepository.findById(1L);
        if (oo.isPresent()) {
            return;
        }

        for (int i = 0; i < 1000; i++) {
            Oht oht = createOht();
            ohtRepository.save(oht);
        }

        for (int i = 0; i < 4000; i++) {
            Wheel wheel = createWheel();

            if (wheel != null) {

                if (i == 3997 || i == 3998 || i == 3999) {
                    LocalDate updateCreatedDate = LocalDate.now().minusYears(3);
                    wheel.changeCreatedDate(updateCreatedDate);
                }
            wheelRepository.save(wheel);

            }
        }

        // jetson 생성
        if (jetsonRepository.count() == 0) {
            Jetson jetson = Jetson.builder()
                    .foupstocker(true)
                    .etching(true)
                    .cleaning(true)
                    .photo(true)
                    .build();
            jetsonRepository.save(jetson);
        }

    }
    int num = 1;
    private Oht createOht() {

        String sNumber = String.format("VM%04d", num);
        List<Integer> repairList = Arrays.asList(996, 997, 998, 999, 1000);

        boolean rep = false;
        if (repairList.contains(num)) {
            rep = true;
        }
        num++;

        Oht oht = Oht.builder()
                .serialNumber(sNumber)
                .maintenance(rep)
                .build();

        return oht;
    }

    Long wNum = 1L;
    private Wheel createWheel() {
        Long ohtNum = Math.floorDiv(wNum, 4) + 1 + (wNum % 4 == 0 ? -1 : 0) ;
        Optional<Oht> ohtOptional = ohtRepository.findById(ohtNum);
        Oht oht = ohtOptional.orElse(null);
        if (oht == null) {
            log.error("Oht not found with id: {}", ohtNum);
            return null;
        }

        String swNumber = String.format("SM%05d", wNum);

        int wheelNum;
        if (wNum.intValue() % 4 == 0) {
            wheelNum = 4;
        } else {
            wheelNum = wNum.intValue() % 4;
        }

        wNum++;

        Wheel wheel = Wheel.builder()
                .oht(oht)
                .serialNumber(swNumber)
                .createdDate(LocalDate.now())
                .position(wheelNum)
                .build();

        return wheel;
    }

    }
}
