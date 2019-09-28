package com.tuguri8.seoulphone.api.interfaces;

import com.tuguri8.seoulphone.api.application.LostSyncService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/v1/lost")
public class LostController {
    private final LostSyncService lostSyncService;

    public LostController(LostSyncService lostSyncService) {this.lostSyncService = lostSyncService;}

    @GetMapping("psync")
    public void syncPhone() {
        lostSyncService.syncPhoneInfo();
    }

    @GetMapping("sync")
    public void syncOther() {
        lostSyncService.syncLostInfo();
    }
}
