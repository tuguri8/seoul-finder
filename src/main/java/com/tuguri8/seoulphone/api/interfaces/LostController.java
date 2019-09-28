package com.tuguri8.seoulphone.api.interfaces;

import com.tuguri8.seoulphone.api.application.LostService;
import com.tuguri8.seoulphone.api.application.LostSyncService;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
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

    public LostController(LostSyncService lostSyncService, LostService lostService) {this.lostSyncService = lostSyncService;
        this.lostService = lostService;
    }

    @GetMapping("search")
    public List<LostInfo> getLostInfo(@RequestParam("START_YMD") String startDate,
                                      @RequestParam("END_YMD") String endDate,
                                      @RequestParam("PRDT_CL_CD_01") String category) {
        return lostService.getLostInfo(startDate, endDate, category);
    }

    @GetMapping("phone")
    public List<PhoneInfo> getPhoneInfo(@RequestParam("START_YMD") String startDate,
                                        @RequestParam("END_YMD") String endDate) {
        return lostService.getPhoneInfo(startDate, endDate);
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
