package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.LostInfoRepository;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.PhoneInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostServicempl implements LostService {
    private final PhoneInfoRepository phoneInfoRepository;
    private final LostInfoRepository lostInfoRepository;

    public LostServicempl(PhoneInfoRepository phoneInfoRepository,
                          LostInfoRepository lostInfoRepository) {
        this.phoneInfoRepository = phoneInfoRepository;
        this.lostInfoRepository = lostInfoRepository;
    }

    @Override
    public List<LostInfo> getLostInfo(String startDate, String endDate, String category) {
        return null;
    }

    @Override
    public List<PhoneInfo> getPhoneInfo(String startDate, String endDate) {
        return null;
    }

}
