package com.ssafy.sebakwi.wheel.controller;

import com.ssafy.sebakwi.checkupList.service.CheckupListService;
import com.ssafy.sebakwi.oht.domain.Oht;
import com.ssafy.sebakwi.oht.domain.OhtRepository;
import com.ssafy.sebakwi.wheel.service.WheelService;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import com.ssafy.sebakwi.wheel.domain.WheelRepository;
import com.ssafy.sebakwi.wheel.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wheels")
public class WheelController {

    private final WheelRepository wheelRepository;
    private final CheckupListService checkupListService;
    private final WheelService wheelService;


    @GetMapping()
    public List<Wheel> wheels() {return wheelRepository.findAll();}

    @GetMapping("/replacement")
    public List<WheelReplacementResponse> wheelReplacement() {
        return wheelService.wheelReplacementInfo();
    }

    /**
     * sse연결하기 전 monthly 데이터 받는 요청
     */
    @GetMapping("/monthly")
    public WheelMonthlyStatusResponse<WheelMonthlyStatus> mainMonthlyWheelStatus() {
        return wheelService.monthlyWheelStatusInfo();
    }

    @GetMapping("/chart")
    public WheelChartResponse getChartInfo() {
        return wheelService.sendChartInfo();
    }

//    @GetMapping("/wheels/chart/initialize")
//    public String initializeWheelChart() {
//        wheelService.initializeWheelChartInfo();
//        return "initialize the chart";
//    }

}
