package com.ssafy.sebakwi.checkupList.controller;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.checkupList.domain.CheckupListRepository;
import com.ssafy.sebakwi.checkupList.dto.CheckupListArrayRequest;
import com.ssafy.sebakwi.checkupList.dto.CheckupListArrayResponse;
import com.ssafy.sebakwi.checkupList.dto.CheckupListDetailModalWheel;
import com.ssafy.sebakwi.checkupList.dto.CheckupListPageResponse;
import com.ssafy.sebakwi.checkupList.service.CheckupListService;
import com.ssafy.sebakwi.wheel.dto.CreateWheelRequest;
import com.ssafy.sebakwi.wheel.dto.CreateWheelResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checkup_list")
public class CheckupListController {

    private final CheckupListService checkupListService;

    @GetMapping
    public CheckupListPageResponse<CheckupListArrayResponse> checkupListArray(
            @RequestParam(value = "isCheckedDate", defaultValue = "false") boolean isCheckedDate,
            @RequestParam(value = "startDateTime", required = false) String startDateTimeStr,
            @RequestParam(value = "endDateTime", required = false) String endDateTimeStr,
            @RequestParam(value = "onlyAbnormal", defaultValue = "false") boolean onlyAbnormal,
            @RequestParam(value = "position", defaultValue = "0") int position,
            @RequestParam(value = "ohtSerialNumber", required = false) String ohtSerialNumber,
            @RequestParam(value = "wheelSerialNumber", required = false) String wheelSerialNumber,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortByCheck", defaultValue = "false") boolean sortByCheck,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc

    ) throws BadRequestException {

        CheckupListArrayRequest request = checkupListService.getCheckupListArrayRequest(isCheckedDate, startDateTimeStr, endDateTimeStr, onlyAbnormal, position, ohtSerialNumber, wheelSerialNumber, page, sortByCheck, desc);

        return checkupListService.getCheckupListArray(request);

    }


    @GetMapping("/{checkupListId}")
    public List<CheckupListDetailModalWheel> checkupListDetailModal(@PathVariable("checkupListId") Long checkupListId) {

        return checkupListService.getCheckupListDetailModalWheels(checkupListId);
    }


    /**
     * 젯슨나노에서 데이터 받을 때
     */

    @PostMapping("/data")
    public CreateWheelResponse saveWheel(@RequestBody @Valid CreateWheelRequest request) {
        log.info("request={}", request);
        return checkupListService.getCreateWheelResponse(request);
    }


}
