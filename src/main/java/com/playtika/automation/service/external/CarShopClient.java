package com.playtika.automation.service.external;

import com.playtika.automation.domain.Car;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient(value = "carShop", url = "http://localhost:2552")
public interface CarShopClient {

    @RequestMapping(method = POST, value = "/cars",
        consumes = APPLICATION_JSON_VALUE)
    long addCar(@RequestBody Car car,
                @RequestParam("price") double price,
                @RequestParam("ownerContacts") String ownerPhone);
}