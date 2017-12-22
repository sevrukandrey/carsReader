package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;


@Component
public class ToCarSaleInfo implements Function<String, CarSaleInfo> {
    @Override
    public CarSaleInfo apply(String line) {
        CarSaleInfo carSaleInfo = new CarSaleInfo();
        String[] split = line.split(",");
        Arrays.stream(split).forEach(aSplit -> {
            carSaleInfo.setCar(new Car(split[0], split[1], split[4], split[2], Integer.parseInt(split[3])));
            carSaleInfo.setPrice(Double.parseDouble(split[6]));
            carSaleInfo.setOwnerContacts(split[5]);
        });
        return carSaleInfo;
    }
}
