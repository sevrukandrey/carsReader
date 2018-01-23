package com.playtika.automation.service.external;

import com.playtika.automation.domain.*;
import com.playtika.automation.service.configuration.CarShopClientConfiguration;
import com.playtika.automation.service.configuration.HystrixClientFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient(name = "CarShop",
    configuration = CarShopClientConfiguration.class,
    fallbackFactory = HystrixClientFallbackFactory.class)
public interface CarShopClient {

    @RequestMapping(method = POST, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    long addCar(@RequestBody Car car,
                @RequestParam("price") double price,
                @RequestParam("ownerContacts") String ownerPhone);

    @RequestMapping(method = GET, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    List<CarSaleInfoResponse> getAllCars();

    @RequestMapping(method = DELETE, value = "/cars/{id}", consumes = APPLICATION_JSON_VALUE)
    void deleteCar(@PathVariable("id") long id);

    @RequestMapping(method = GET, value = "/cars/{id}", consumes = APPLICATION_JSON_VALUE)
    SaleInfo getCarDetails(@PathVariable("id") long id);

    @RequestMapping(method = POST, value = "/rejectDeal", consumes = APPLICATION_JSON_VALUE)
    void rejectDeal(@RequestParam("dealId") long id);

    @RequestMapping(method = PUT, value = "/car", consumes = APPLICATION_JSON_VALUE)
    long putCarToSale(@RequestBody CarOnSaleRequest carOnSaleRequest);

    @RequestMapping(method = GET, value = "/bestDeal", consumes = APPLICATION_JSON_VALUE)
    long chooseBestDeal(@RequestParam("advertId") long advertId);

    @RequestMapping(method = POST, value = "/deal", consumes = APPLICATION_JSON_VALUE)
    long createDeal(@RequestParam("advertId") long advertId,
                    @RequestBody DealRequest dealRequest);

}

