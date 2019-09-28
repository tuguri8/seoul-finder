package com.tuguri8.seoulphone.api.datatool.opendata;

import com.tuguri8.seoulphone.api.datatool.opendata.dto.SearchLosResponse;
import com.tuguri8.seoulphone.api.datatool.opendata.dto.SearchPhoneResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "opendata-client", url = "http://apis.data.go.kr")
public interface OpenDataClient {

    @GetMapping("1320000/SearchMoblphonInfoInqireService/getMoblphonAcctoKindAreaPeriodInfo")
    SearchPhoneResponse searchPhone(@RequestParam("serviceKey") String key,
                                    @RequestParam("FD_LCT_CD") String locationCode,
                                    @RequestParam("START_YMD") String startYmd,
                                    @RequestParam("END_YMD") String endYmd,
                                    @RequestParam("pageNo") String pageNo);

    @GetMapping("1320000/LosfundInfoInqireService/getLosfundInfoAccToClAreaPd")
    SearchLosResponse searchLos(@RequestParam("serviceKey") String key,
                                @RequestParam("FD_LCT_CD") String locationCode,
                                @RequestParam("START_YMD") String startYmd,
                                @RequestParam("END_YMD") String endYmd,
                                @RequestParam("pageNo") String pageNo);
}
