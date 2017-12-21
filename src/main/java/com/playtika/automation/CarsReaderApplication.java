package com.playtika.automation;

import com.playtika.automation.service.external.CarShopClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
public class CarsReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsReaderApplication.class, args);
	}
}
