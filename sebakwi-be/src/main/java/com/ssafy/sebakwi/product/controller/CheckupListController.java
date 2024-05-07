package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.domain.CheckupList;
import com.ssafy.sebakwi.product.domain.CheckupListRepository;
import com.ssafy.sebakwi.product.dto.*;
import com.ssafy.sebakwi.product.service.CheckupListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checkup_list")
public class CheckupListController {

    private final CheckupListRepository checkupListRepository;
    private final CheckupListService checkupListService;

    @GetMapping
    public CheckupListPageResponse<CheckupListArrayResponse> checkupListArray(@Validated @RequestBody CheckupListArrayRequest request) throws BadRequestException {

        return checkupListService.findCheckupListArray(request);

    }


    @GetMapping("/{checkupListId}")
    public CheckupListDetailModalResponse checkupListDetailModal(@PathVariable("checkupListId") int checkupListId) {

        Optional<CheckupList> fCheckupList = checkupListRepository.findById(checkupListId);
        log.info("fCheckupList={}",fCheckupList);
        if (fCheckupList.isPresent()) {

            CheckupList findCheckupList = fCheckupList.get();

            OhtDTO ohtDTO = OhtDTO.builder()
                    .id(findCheckupList.getWheel().getOht().getId())
                    .serialNumber(findCheckupList.getWheel().getOht().getSerialNumber())
                    .maintenance(findCheckupList.getWheel().getOht().isMaintenance())
                    .build();

            WheelDTO wheelDTO = WheelDTO.builder()
                    .oht(ohtDTO)
                    .serialNumber(findCheckupList.getWheel().getSerialNumber())
//                    .currentStatus(findCheckupList.getWheel().getCurrentStatus())
                    .createdDate(findCheckupList.getWheel().getCreatedDate())
                    .position(findCheckupList.getWheel().getPosition())
                    .build();

            CheckupListModalDto checkupListDTO = CheckupListModalDto.builder()
                    .wheel(wheelDTO)
                    .checkedDate(findCheckupList.getCheckedDate())
                    .wheelImage(findCheckupList.getWheelImage())
                    .status(findCheckupList.getStatus())
                    .diameter(findCheckupList.getDiameter())
                    .crack(findCheckupList.isCrack())
                    .stamp(findCheckupList.isStamp())
                    .peeling(findCheckupList.isPeeling())
                    .build();

            CheckupListDetailModalResponse ModalResponse = CheckupListDetailModalResponse.builder()
                    .wheelNumber(checkupListDTO.getWheel().getSerialNumber())
                    .position(checkupListDTO.getWheel().getPosition())
                    .ohtNumber(checkupListDTO.getWheel().getOht().getSerialNumber())
                    .checkedDate(checkupListDTO.getCheckedDate())
                    .wheelImage(checkupListDTO.getWheelImage())
                    .diameter(checkupListDTO.getDiameter())
                    .crack(checkupListDTO.isCrack())
                    .stamp(checkupListDTO.isStamp())
                    .peeling(checkupListDTO.isPeeling())
                    .status(checkupListDTO.getStatus())
                    .createdDate(checkupListDTO.getWheel().getCreatedDate())
                    .build();

            return ModalResponse;
        } else {
            throw new RuntimeException();
        }
    }
}
