package com.playtika.automation.service.configuration;

import com.playtika.automation.service.CarsReaderService;
import com.playtika.automation.service.external.CarShopClient;
import com.playtika.automation.web.CarsReaderController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = CarShopClient.class)
@EnableAutoConfiguration
public class CarShopClientConfiguration {

}