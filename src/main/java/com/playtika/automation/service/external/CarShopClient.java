package com.playtika.automation.service.external;

import com.playtika.automation.domain.Car;
import com.playtika.automation.service.configuration.CarShopClientConfiguration;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "CarShop",
    configuration = CarShopClientConfiguration.class,
    fallbackFactory = CarShopClient.HystrixClientFallbackFactory.class)
public interface CarShopClient {

    @RequestMapping(method = POST, value = "/cars",
        consumes = APPLICATION_JSON_VALUE)
    long addCar(@RequestBody Car car,
                @RequestParam("price") double price,
                @RequestParam("ownerContacts") String ownerPhone);


    @Slf4j
    class HystrixClientFallbackFactory implements FallbackFactory<CarShopClient> {

        @Override
        public CarShopClient create(Throwable cause) {
            return (car, price, ownerPhone) -> {
                log.error("Histrix `addCar` command failed: car {} price: {} ownerContacts {} errorMessage {}", car, price, ownerPhone, cause.getMessage());
                return -1;
            };
        }
    }

}
