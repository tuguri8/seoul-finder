package com.tuguri8.seoulphone.api.datatool;

import com.tuguri8.seoulphone.api.datatool.opendata.OpenDataClient;
import feign.Feign;
import feign.Retryer;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.commons.lang.CharEncoding.UTF_8;

@EnableFeignClients
@Configuration
public class DataToolConfig {

    @Bean
    public OpenDataClient openDataClient() {
        return Feign.builder()
                    .decoder(new JAXBDecoder(new JAXBContextFactory.Builder()
                                                 .withMarshallerJAXBEncoding(UTF_8)
                                                 .build()))
                    .contract(new SpringMvcContract())
                    .retryer(new Retryer.Default())
                    .target(OpenDataClient.class, "opendata-client");
    }
}
