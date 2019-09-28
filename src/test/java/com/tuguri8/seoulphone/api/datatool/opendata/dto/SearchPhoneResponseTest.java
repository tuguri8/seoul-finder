package com.tuguri8.seoulphone.api.datatool.opendata.dto;

import com.tuguri8.seoulphone.api.datatool.opendata.OpenDataClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchPhoneResponseTest {
    @Value("${opendata-client.key}")
    String key;

    @Autowired
    OpenDataClient openDataClient;

    @Test
    public void searchPhone() {
        openDataClient.searchPhone(key, "LCA000", "20190901", "20190927");
    }

    @Test
    public void searchLos() {
        openDataClient.searchLos(key, "LCA000", "20190901", "20190927", "PRH000");
    }

}