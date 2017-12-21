package com.playtika.automation.service.configuration;

import com.playtika.automation.service.external.CarShopClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = CarShopClient.class)
public class CarShopClientConfiguration {
}