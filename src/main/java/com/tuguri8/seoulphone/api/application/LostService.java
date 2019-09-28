package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;

import java.util.List;

public interface LostService {
    List<LostInfo> getLostInfo(String startDate, String endDate, String category);
    List<PhoneInfo> getPhoneInfo(String startDate, String endDate);
}
