package com.playtika.automation.service.configuration;

import com.playtika.qa.carsclient.CarShopClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = CarShopClient.class)
public class CarShopClientConfiguration {
}