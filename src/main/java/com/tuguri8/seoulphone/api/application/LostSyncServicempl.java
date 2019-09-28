package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.datatool.opendata.OpenDataClient;
import com.tuguri8.seoulphone.api.datatool.opendata.dto.SearchLosResponse;
import com.tuguri8.seoulphone.api.datatool.opendata.dto.SearchPhoneResponse;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.LostInfoRepository;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.PhoneInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LostSyncServicempl implements LostSyncService {
    @Value("${opendata-client.key}")
    private String key;
    private final String LOCATION_CODE = "LCA000";
    private final OpenDataClient openDataClient;
    private final ModelMapper modelMapper;
    private final PhoneInfoRepository phoneInfoRepository;
    private final LostInfoRepository lostInfoRepository;

    public LostSyncServicempl(OpenDataClient openDataClient,
                              ModelMapper modelMapper, PhoneInfoRepository phoneInfoRepository,
                              LostInfoRepository lostInfoRepository) {
        this.openDataClient = openDataClient;
        this.modelMapper = modelMapper;
        this.phoneInfoRepository = phoneInfoRepository;
        this.lostInfoRepository = lostInfoRepository;
    }

    @Override
    public void syncPhoneInfo() {
        SearchPhoneResponse searchPhoneResponse = openDataClient.searchPhone(key, LOCATION_CODE, getToday(), getToday());
        List<PhoneInfo> phoneInfoList = searchPhoneResponse.getBody()
                                                           .getItems()
                                                           .getItem()
                                                           .stream()
                                                           .map(x -> modelMapper.map(x, PhoneInfo.class))
                                                           .collect(Collectors.toList());
        phoneInfoRepository.saveAll(phoneInfoList);
    }

    @Override
    public void syncLostInfo() {
        SearchLosResponse searchLosResponse = openDataClient.searchLos(key, LOCATION_CODE, getToday(), getToday());
        List<LostInfo> lostInfoList = searchLosResponse.getBody()
                                                        .getItems()
                                                        .getItem()
                                                        .stream()
                                                        .map(x -> modelMapper.map(x, LostInfo.class))
                                                        .collect(Collectors.toList());
        lostInfoRepository.saveAll(lostInfoList);
    }

    private String getToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
        return LocalDate.now().format(formatter);
    }
}
