package com.tuguri8.seoulphone.api.application;

import com.google.common.collect.Lists;
import com.tuguri8.seoulphone.api.datatool.opendata.OpenDataClient;
import com.tuguri8.seoulphone.api.datatool.opendata.dto.SearchLosResponse;
import com.tuguri8.seoulphone.api.datatool.opendata.dto.SearchPhoneResponse;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.Category;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.LostInfoRepository;
import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository.PhoneInfoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LostSyncServicempl implements LostSyncService {
    private static final Logger log = LoggerFactory.getLogger(LostSyncServicempl.class);
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
        SearchPhoneResponse searchPhoneResponse = openDataClient.searchPhone(key, LOCATION_CODE, getToday(), getToday(), "1");
        int pageSize = (Integer.parseInt(searchPhoneResponse.getBody().getTotalCount()) / 10) + 1;
        List<PhoneInfo> phoneInfoList = Lists.newArrayList();
        for (int i = 1; i <= pageSize; i++) {
            SearchPhoneResponse phoneResponse = openDataClient.searchPhone(key, LOCATION_CODE, getToday(), getToday(), String.valueOf(i));
            phoneInfoList.addAll(phoneResponse.getBody()
                                              .getItems()
                                              .getItem()
                                              .stream()
                                              .map(this::transform)
                                              .collect(Collectors.toList()));
        }

        List<PhoneInfo> oldPhoneInfoList = phoneInfoRepository.findAll();
        phoneInfoList.removeIf(data -> oldPhoneInfoList.stream().anyMatch(oldData -> data.getAtcId().equals(oldData.getAtcId())));
        phoneInfoRepository.saveAll(phoneInfoList);
        log.info(phoneInfoList.size() + " 개의 핸드폰 분실물 저장완료");
    }

    @Override
    public void syncLostInfo() {
        SearchLosResponse searchLosResponse = openDataClient.searchLos(key, LOCATION_CODE, getToday(), getToday(), "1");
        int pageSize = (Integer.parseInt(searchLosResponse.getBody().getTotalCount()) / 10) + 1;
        List<LostInfo> lostInfoList = Lists.newArrayList();
        for (int i = 1; i <= pageSize; i++) {
            SearchLosResponse losResponse = openDataClient.searchLos(key, LOCATION_CODE, getToday(), getToday(), String.valueOf(i));
            lostInfoList.addAll(losResponse.getBody()
                                           .getItems()
                                           .getItem()
                                           .stream()
                                           .map(this::transform)
                                           .collect(Collectors.toList()));
        }

        List<LostInfo> oldLostInfoList = lostInfoRepository.findAll();
        lostInfoList.removeIf(data -> oldLostInfoList.stream().anyMatch(oldData -> data.getAtcId().equals(oldData.getAtcId())));
        lostInfoRepository.saveAll(lostInfoList);
        log.info(lostInfoList.size() + " 개의 일반 분실물 저장완료");
    }

    private String getToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
        return LocalDate.now().format(formatter);
    }

    private PhoneInfo transform(SearchPhoneResponse.Item item) {
        PhoneInfo phoneInfo = new PhoneInfo();
        phoneInfo.setAtcId(item.getAtcId());
        phoneInfo.setDepPlace(item.getDepPlace());
        phoneInfo.setFdFilePathImg(item.getFdFilePathImg());
        phoneInfo.setFdPrdtNm(item.getFdPrdtNm());
        phoneInfo.setFdSbjt(item.getFdSbjt());
        phoneInfo.setFdSn(item.getFdSn());
        phoneInfo.setFdYmd(item.getFdYmd());
        phoneInfo.setMdcd(item.getMdcd());
        phoneInfo.setPrdtClNm(item.getPrdtClNm());
        phoneInfo.setRnum(item.getRnum());
        phoneInfo.setSrno(item.getSrno());
        phoneInfo.setCategory("PRJ000");
        return phoneInfo;
    }

    private LostInfo transform(SearchLosResponse.Item item) {
        LostInfo lostInfo = new LostInfo();
        lostInfo.setAtcId(item.getAtcId());
        lostInfo.setDepPlace(item.getDepPlace());
        lostInfo.setFdFilePathImg(item.getFdFilePathImg());
        lostInfo.setFdPrdtNm(item.getFdPrdtNm());
        lostInfo.setFdSbjt(item.getFdSbjt());
        lostInfo.setFdSn(item.getFdSn());
        lostInfo.setFdYmd(item.getFdYmd());
        lostInfo.setPrdtClNm(item.getPrdtClNm());
        lostInfo.setRnum(item.getRnum());
        lostInfo.setCategory(Category.getCategoryFromName(item.getPrdtClNm().substring(0, item.getPrdtClNm().indexOf(" >"))).getCode());
        return lostInfo;
    }
}
