package com.ssafy.sebakwi.util;

import com.ssafy.sebakwi.product.domain.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void dbInit() {
        Optional<Oht> oo = ohtRepository.findById(1L);
        if (oo.isPresent()) {
            return;
        }

        for (int i = 0; i < 30; i++) {
            Oht oht = createOht();
            ohtRepository.save(oht);
        }

        for (int i = 0; i < 120; i++) {
            Wheel wheel = createWheel();
            if (wheel != null) {
            wheelRepository.save(wheel);
            log.info(wheel.getOht().getSerialNumber());

            }
        }
    }
    int num = 1;
    private Oht createOht() {

        String sNumber = String.format("VM%04d", num);
        List<Integer> repairList = Arrays.asList(296, 297, 298, 299, 300);
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
        Long ohtNum = Math.floorDiv(wNum, 4) + 1;
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
//                .currentStatus(WheelStatus.NORMAL)
                .createdDate(LocalDate.now())
                .position(wheelNum)
                .build();

        return wheel;
    }

    }
}
