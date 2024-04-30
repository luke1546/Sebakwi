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
import java.util.Optional;

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
        Optional<Oht> oo = ohtRepository.findById(1L);
        if (oo.isPresent()) {
            return;
        }

        for (int i = 0; i < 300; i++) {
            Oht oht = createOht();
            ohtRepository.save(oht);
        }
    }
    int num = 1;
    private Oht createOht() {

        String sNumber = String.format("VM%05d", num);
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

    }
}
