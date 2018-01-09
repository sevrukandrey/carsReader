package com.playtika.automation.service.configuration;

import com.playtika.automation.domain.Car;
import com.playtika.automation.service.external.CarShopClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HystrixClientFallbackFactory implements FallbackFactory<CarShopClient> {

    @Override
    public CarShopClient create(Throwable cause) {
        return (Car car, double price, String ownerPhone) -> {
            log.error("Histrix `addCar` command failed: car {} price: {} ownerContacts {} errorMessage {}",
                    car, price, ownerPhone, cause.getMessage());
            return -1;
        };
    }
}
