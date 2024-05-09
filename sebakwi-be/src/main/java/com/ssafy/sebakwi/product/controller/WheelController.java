package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.domain.*;
import com.ssafy.sebakwi.product.dto.*;
import com.ssafy.sebakwi.product.service.MainService;
import com.ssafy.sebakwi.product.service.WheelService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WheelController {

    private final OhtRepository ohtRepository;
    private final WheelRepository wheelRepository;
    private final CheckupListRepository checkupListRepository;
    private final WheelService wheelService;

    @GetMapping("/ohts")
    public List<Oht> ohts() {
        return ohtRepository.findAll();
    }

    @GetMapping("/wheels")
    public List<Wheel> wheels() {return wheelRepository.findAll();}

    @GetMapping("/wheels/replacement")
    public List<WheelReplacementResponse> wheelReplacement() {
        return wheelService.wheelReplacementInfo();
    }

    /**
     * 메인페이지
     */
    @GetMapping("/wheels/monthly")
    public WheelMonthlyStatusResponse<WheelMonthlyStatus> mainMonthlyWheelStatus() {
        return wheelService.monthlyWheelStatusInfo();
    }

    @GetMapping("/wheels/chart")
    public WheelChartResponse getChartInfo() {
        return wheelService.wheelChartInfo();
    }

    @GetMapping("/wheels/chart/initialize")
    public String initializeWheelChart() {
        wheelService.initializeWheelChartInfo();
        return "initialize the chart";
    }

    /**
     * 젯슨나노
     */

    @PostMapping("/wheels")
    public CreateWheelResponse saveWheel(@RequestBody @Valid CreateWheelRequest request) {
        WheelStatus wheelStatus = WheelStatus.NORMAL;
        if (request.crack || request.stamp || request.peeling) {
            wheelStatus = WheelStatus.ABNORMAL;
        }

        Wheel checkedWheel = wheelRepository.findByWheelSerialNumber(request.getWheelSerialNumber());
        Oht checkedOht = ohtRepository.findByOhtSerialNumber(request.getOhtSerialNumber());

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
        log.info("checkupListDTO={}",checkupListDTO);

        if (wheelStatus == WheelStatus.ABNORMAL) {

            wheelService.updateMonthlyStatus(checkupListDTO);
        }

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

        public CreateWheelResponse(String serialNumber) {
            this.serialNumber = serialNumber;
        }
    }

}
