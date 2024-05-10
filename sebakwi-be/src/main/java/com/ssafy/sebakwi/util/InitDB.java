package com.ssafy.sebakwi.util;

import com.ssafy.sebakwi.checkupList.controller.CheckupListController;
import com.ssafy.sebakwi.checkupList.service.CheckupListService;
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

    private final CheckupListController checkupListController;

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

                if (i == 119 || i == 118) {
                    LocalDate updateCreatedDate = LocalDate.now().minusYears(3);
                    wheel.changeCreatedDate(updateCreatedDate);
                }
            wheelRepository.save(wheel);

            }
        }

        /**
         * checkupList 더미데이터 만들기
         */

        List<String> ohtNumber = Arrays.asList("VM0006","VM0006","VM0006","VM0006","VM0007","VM0007","VM0007","VM0007", "VM0008");
        List<String> wheelNumber = Arrays.asList("SM00021","SM00022","SM00023","SM00024","SM00025","SM00026","SM00027","SM00028", "SM00029");
        List<Integer> position = Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4, 1);
        List<String> wheelImage = Arrays.asList("","","","","","","","", "");
        List<Float> diameter = Arrays.asList(0.7f,0.7f,0.7f,0.7f,0.7f,0.7f,0.7f,0.7f, 0.7f);
        List<Boolean> crack = Arrays.asList(false,false,true,false,true,false,false,false, false);
        List<Boolean> stamp = Arrays.asList(false,false,true,false,false,false,true,false, false);
        List<Boolean>  peeling = Arrays.asList(true,false,false,false,false,true,false,false, false);

        for (int i = 0; i < 9; i++) {
            CreateWheelRequest request = CreateWheelRequest.builder()
                    .ohtSerialNumber(ohtNumber.get(i))
                    .wheelSerialNumber(wheelNumber.get(i))
                    .position(position.get(i))
                    .wheelImage(wheelImage.get(i))
                    .diameter(diameter.get(i))
                    .crack(crack.get(i))
                    .stamp(stamp.get(i))
                    .peeling(peeling.get(i))
                    .build();

            checkupListController.saveWheel(request);
        }



    }
    int num = 1;
    private Oht createOht() {

        String sNumber = String.format("VM%04d", num);
//        List<Integer> repairList = Arrays.asList(296, 297, 298, 299, 300);
        List<Integer> repairList = Arrays.asList(29, 30);
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
//                .currentStatus(WheelStatus.NORMAL)
                .createdDate(LocalDate.now())
                .position(wheelNum)
                .build();

        return wheel;
    }

    }
}
