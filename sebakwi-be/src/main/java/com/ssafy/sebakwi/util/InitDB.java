package com.ssafy.sebakwi.util;

import com.ssafy.sebakwi.product.domain.Oht;
import com.ssafy.sebakwi.product.domain.OhtRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
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
    public void dbInit() {
        for (int i = 0; i < 300; i++) {
            Oht oht = createOht();
            ohtRepository.save(oht);
        }
    }

    private Oht createOht() {
        int num = 1;
        String serialNumber = String.format("VM%05d", num);
        num++;
        List<Integer> repairList = Arrays.asList(1, 2, 3, 4, 5);
        boolean rep = false;
        if (repairList.contains(num)) {
            rep = true;
        }

        Oht oht = Oht.builder()
                .serialNumber(serialNumber)
                .repair(rep)
                .build();

        return oht;
    }



    }
}
