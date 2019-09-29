package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.LostInfoRepository;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.PhoneInfoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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

    @Cacheable(value = "getLostInfo")
    @Override
    public List<LostInfo> getLostInfo(String startDate, String endDate, String category, Pageable pageable) {
        return lostInfoRepository.findAllByCategoryAndFdYmdBetween(category,
                                                                   stringToLocalDate(startDate),
                                                                   stringToLocalDate(endDate),
                                                                   pageable)
                                 .orElse(Collections.emptyList());
    }

    @Cacheable(value = "getPhoneInfo")
    @Override
    public List<PhoneInfo> getPhoneInfo(String startDate, String endDate, Pageable pageable) {
        return phoneInfoRepository.findAllByFdYmdBetween(stringToLocalDate(startDate), stringToLocalDate(endDate), pageable)
                                  .orElse(Collections.emptyList());
    }

    @Override
    public List<LostInfo> searchLostInfo(String category, String name, Pageable pageable) {
        return lostInfoRepository.findAllByCategoryAndFdPrdtNmContaining(category, name, pageable).orElse(Collections.emptyList());
    }

    @Override
    public List<PhoneInfo> searchPhoneInfo(String name, Pageable pageable) {
        return phoneInfoRepository.findAllByFdPrdtNmContaining(name, pageable).orElse(Collections.emptyList());
    }

    private LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
