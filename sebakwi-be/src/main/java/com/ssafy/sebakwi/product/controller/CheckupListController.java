package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.domain.CheckupList;
import com.ssafy.sebakwi.product.domain.CheckupListRepository;
import com.ssafy.sebakwi.product.dto.CheckupListDTO;
import com.ssafy.sebakwi.product.dto.CheckupListModalDto;
import com.ssafy.sebakwi.product.dto.OhtDTO;
import com.ssafy.sebakwi.product.dto.WheelDTO;
import com.ssafy.sebakwi.util.dto.CheckupListDetailModalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CheckupListController {

    private final CheckupListRepository checkupListRepository;

    @GetMapping("/api/checkup-list")
    public List<CheckupList> chekcupListArray() {

        List<CheckupList> findAll = checkupListRepository.findAll();
        return findAll;
    }


    @GetMapping("/api/checkup-list/{checkupListId}")
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
                    .currentStatus(findCheckupList.getWheel().getCurrentStatus())
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
                    .status(checkupListDTO.getStatus())
                    .createdDate(checkupListDTO.getWheel().getCreatedDate())
                    .build();

            return ModalResponse;
        } else {
            throw new RuntimeException();
        }
    }
}
