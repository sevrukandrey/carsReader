package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
@Slf4j
public class ToCarSaleInfo implements Function<String, CarSaleInfo> {

    @Override
    public CarSaleInfo apply(String line) {

        log.debug("Start composing CarSaleInfo from line");

        String[] split = line.split(",");
        Car car = new Car(split[0], split[1], split[4], split[2], Integer.parseInt(split[3]));
        double price = Double.parseDouble(split[6]);
        String ownerContacts = split[5];

        return new CarSaleInfo(car, ownerContacts, price);
    }
}
