package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.LostInfoRepository;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.PhoneInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class LostServicempl implements LostService {
    private static final Logger log = LoggerFactory.getLogger(LostServicempl.class);
    private final PhoneInfoRepository phoneInfoRepository;
    private final LostInfoRepository lostInfoRepository;

    public LostServicempl(PhoneInfoRepository phoneInfoRepository,
                          LostInfoRepository lostInfoRepository) {
        this.phoneInfoRepository = phoneInfoRepository;
        this.lostInfoRepository = lostInfoRepository;
    }

    @Cacheable(value = "getLostInfo")
    @Override
    public LostInfoListResponse getLostInfo(String startDate, String endDate, String category, Pageable pageable) {
        LostInfoListResponse lostInfoListResponse = new LostInfoListResponse();
        List<LostInfo> lostInfoList = lostInfoRepository.findAllByCategoryAndFdYmdBetween(category,
                                                                                          stringToLocalDate(startDate),
                                                                                          stringToLocalDate(endDate),
                                                                                          pageable)
                                                        .orElse(Collections.emptyList());
        lostInfoListResponse.setItems(lostInfoList);
        lostInfoListResponse.setTotalCount(lostInfoRepository.countByCategoryAndFdYmdBetween(category,
                                                                                             stringToLocalDate(startDate),
                                                                                             stringToLocalDate(endDate)));
        lostInfoListResponse.setPageNo(pageable.getPageNumber() + 1);
        lostInfoListResponse.setNumOfRows(pageable.getPageSize());
        return lostInfoListResponse;
    }

    @Cacheable(value = "getPhoneInfo")
    @Override
    public PhoneInfoListResponse getPhoneInfo(String startDate, String endDate, Pageable pageable) {
        PhoneInfoListResponse phoneInfoListResponse = new PhoneInfoListResponse();
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByFdYmdBetween(stringToLocalDate(startDate),
                                                                                  stringToLocalDate(endDate),
                                                                                  pageable)
                                                           .orElse(Collections.emptyList());
        phoneInfoListResponse.setItems(phoneInfoList);
        phoneInfoListResponse.setTotalCount(phoneInfoRepository.countByFdYmdBetween(stringToLocalDate(startDate),
                                                                                    stringToLocalDate(endDate)));
        phoneInfoListResponse.setPageNo(pageable.getPageNumber() + 1);
        phoneInfoListResponse.setNumOfRows(pageable.getPageSize());
        return phoneInfoListResponse;
    }

    @Override
    public LostInfoListResponse searchLostInfo(String category, String name, Pageable pageable) {
        LostInfoListResponse lostInfoListResponse = new LostInfoListResponse();
        List<LostInfo> lostInfoList = lostInfoRepository.findAllByCategoryAndFdPrdtNmContaining(category, name, pageable)
                                                        .orElse(Collections.emptyList());
        lostInfoListResponse.setItems(lostInfoList);
        lostInfoListResponse.setTotalCount(lostInfoRepository.countByCategoryAndFdPrdtNmContaining(category, name));
        lostInfoListResponse.setPageNo(pageable.getPageNumber() + 1);
        lostInfoListResponse.setNumOfRows(pageable.getPageSize());
        return lostInfoListResponse;
    }

    @Override
    public PhoneInfoListResponse searchPhoneInfo(String name, Pageable pageable) {
        PhoneInfoListResponse phoneInfoListResponse = new PhoneInfoListResponse();
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByFdPrdtNmContaining(name, pageable).orElse(Collections.emptyList());
        phoneInfoListResponse.setItems(phoneInfoList);
        phoneInfoListResponse.setTotalCount(phoneInfoRepository.countByFdPrdtNmContaining(name));
        phoneInfoListResponse.setPageNo(pageable.getPageNumber() + 1);
        phoneInfoListResponse.setNumOfRows(pageable.getPageSize());
        return phoneInfoListResponse;
    }

    private LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
