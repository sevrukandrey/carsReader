package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;


@Component
public abstract class ToCarSaleInfo implements Function {

    public CarSaleInfo apply(String line) {
        CarSaleInfo carSaleInfo = new CarSaleInfo();
        String[] split = line.split(",");
        for (String aSplit : split) {
            carSaleInfo.setCar(new Car(split[0], split[1], split[2], split[3], Integer.parseInt(split[4])));
            carSaleInfo.setPrice(Double.parseDouble(split[6]));
            carSaleInfo.setOwnerContacts(split[5]);
        }
        return carSaleInfo;
    }
}
