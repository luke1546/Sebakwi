package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.domain.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WheelController {

    private final OhtRepository ohtRepository;
    private final WheelRepository wheelRepository;
    private final CheckupListRepository checkupListRepository;

//    @PostMapping("/api/wheels")
//    public CreateWheelResponse saveWheel(@RequestBody @Valid CreateWheelRequest request) {
//
//        WheelStatus wheelStatus = WheelStatus.NORMAL;
//        // 상태 확인
//        if (request.crack || request.stamp || request.peeling) {
//            wheelStatus = WheelStatus.ABNORMAL;
//        }
//
//        // 최초로 검사한 wheel인지 확인
//        Wheel checkedWheel = wheelRepository.findByWheelSerialNumber(request.getWheelSerialNumber());
//        Oht checkedOht = ohtRepository.findByOhtSerialNumber(request.getOhtSerialNumber());
//        if (checkedWheel == null) {
//            Wheel wheel = Wheel.builder()
//                    .oht(checkedOht)
//                    .serialNumber(request.getWheelSerialNumber())
//                    .currentStatus(wheelStatus)
//                    .position(request.getPosition())
//                    .build();
//
//            wheelRepository.save(wheel);
//        }
//
//        CheckupList checkupList = CheckupList.builder()
//                .wheel(checkedWheel)
//                .wheelImage(request.wheelImage)
//                .status(wheelStatus)
//                .diameter(request.diameter)
//                .crack(request.crack)
//                .stamp(request.stamp)
//                .peeling(request.peeling)
//                .build();
//
//        checkupListRepository.save(checkupList);
//
//        return new CreateWheelResponse(request.getWheelSerialNumber());
//    }
//
//
//    @Data
//    static class CreateWheelRequest {
//
//        @NotEmpty
//        private String ohtSerialNumber;
//        @NotEmpty
//        private String WheelSerialNumber;
//        private int position;
//
//        private String wheelImage;
//        private float diameter;
//        private boolean crack;
//        private boolean stamp;
//        private boolean peeling;
//    }
//
//    @Data
//    static class CreateWheelResponse {
//        private String serialNumber;
//
//        public CreateWheelResponse(String serialNumber) {
//            this.serialNumber = serialNumber;
//        }
//    }

}
