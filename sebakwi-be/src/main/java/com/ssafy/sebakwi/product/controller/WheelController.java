package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.domain.*;
import com.ssafy.sebakwi.product.dto.CheckupListDTO;
import com.ssafy.sebakwi.product.dto.OhtDTO;
import com.ssafy.sebakwi.product.dto.WheelDTO;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WheelController {

    private final EntityManager em;
    private final OhtRepository ohtRepository;
    private final WheelRepository wheelRepository;
    private final CheckupListRepository checkupListRepository;

    @GetMapping("/api/ohts")
    public List<Oht> ohts() {
        return ohtRepository.findAll();
    }

    @GetMapping("/api/wheels")
    public List<Wheel> wheels() {return wheelRepository.findAll();}

    @PostMapping("/api/wheels")
    public CreateWheelResponse saveWheel(@RequestBody @Valid CreateWheelRequest request) {
        WheelStatus wheelStatus = WheelStatus.NORMAL;
        if (request.crack || request.stamp || request.peeling) {
            wheelStatus = WheelStatus.ABNORMAL;
        }

        Wheel checkedWheel = wheelRepository.findByWheelSerialNumber(request.getWheelSerialNumber());
        Oht checkedOht = ohtRepository.findByOhtSerialNumber(request.getOhtSerialNumber());

        if (checkedWheel == null) {
            OhtDTO ohtDTO = OhtDTO.builder()
                    .id(checkedOht.getId())
                    .serialNumber(checkedOht.getSerialNumber())
                    .maintenance(checkedOht.isMaintenance())
                    .build();

            WheelDTO wheelDTO = WheelDTO.builder()
                    .oht(ohtDTO)
                    .serialNumber(request.getWheelSerialNumber())
                    .currentStatus(wheelStatus)
                    .createdDate(LocalDate.now())
                    .position(request.getPosition())
                    .build();

            checkedWheel = wheelRepository.save(wheelDTO.toEntity());
        }

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter createdTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        CheckupListDTO checkupListDTO = CheckupListDTO.builder()
                .wheel(checkedWheel)
                .checkedDate(LocalDateTime.now())
                .wheelImage(request.wheelImage)
                .status(wheelStatus)
                .diameter(request.diameter)
                .crack(request.crack)
                .stamp(request.stamp)
                .peeling(request.peeling)
                .build();

        checkupListRepository.save(checkupListDTO.toEntity());

        return new CreateWheelResponse(request.getWheelSerialNumber());
    }


    @Data
    static class CreateWheelRequest {

        @NotEmpty
        private String ohtSerialNumber;
        @NotEmpty
        private String WheelSerialNumber;
        private int position;

        private String wheelImage;
        private float diameter;
        private boolean crack;
        private boolean stamp;
        private boolean peeling;
    }

    @Data
    static class CreateWheelResponse {
        private String serialNumber;
//        private Wheel wheel;

        public CreateWheelResponse(String serialNumber) {
            this.serialNumber = serialNumber;
//            this.wheel = wheel;
        }
    }

}
