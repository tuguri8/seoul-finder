package com.tuguri8.seoulphone.api.interfaces;

import com.tuguri8.seoulphone.api.application.LostInfoListResponse;
import com.tuguri8.seoulphone.api.application.LostService;
import com.tuguri8.seoulphone.api.application.LostSyncService;
import com.tuguri8.seoulphone.api.application.PhoneInfoListResponse;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/v1/lost")
public class LostController {
    private final LostSyncService lostSyncService;
    private final LostService lostService;

    public LostController(LostSyncService lostSyncService, LostService lostService) {
        this.lostSyncService = lostSyncService;
        this.lostService = lostService;
    }

    @GetMapping("other/list")
    public LostInfoListResponse getLostInfo(@RequestParam("START_YMD") String startDate,
                                            @RequestParam("END_YMD") String endDate,
                                            @RequestParam("PRDT_CL_CD_01") String category,
                                            Pageable pageable) {
        return lostService.getLostInfo(startDate, endDate, category, pageable);
    }

    @GetMapping("other/search")
    public LostInfoListResponse searchLostInfo(@RequestParam("category") String category,
                                         @RequestParam("name") String name,
                                         Pageable pageable) {
        return lostService.searchLostInfo(category, name, pageable);
    }

    @GetMapping("phone/list")
    public PhoneInfoListResponse getPhoneInfo(@RequestParam("START_YMD") String startDate,
                                              @RequestParam("END_YMD") String endDate,
                                              Pageable pageable) {
        return lostService.getPhoneInfo(startDate, endDate, pageable);
    }

    @GetMapping("phone/search")
    public PhoneInfoListResponse searchPhoneInfo(@RequestParam("name") String name,
                                           Pageable pageable) {
        return lostService.searchPhoneInfo(name, pageable);
    }

    @GetMapping("psync")
    public void syncPhone() {
        lostSyncService.syncPhoneInfo();
    }

    @GetMapping("sync")
    public void syncOther() {
        lostSyncService.syncLostInfo();
    }

}
