package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LostService {
    List<LostInfo> getLostInfo(String startDate, String endDate, String category, Pageable pageable);
    List<PhoneInfo> getPhoneInfo(String startDate, String endDate, Pageable pageable);
}
