package com.playtika.automation.service;

import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.external.CarShopClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@AllArgsConstructor
public class ConsumeCarSaleInfo implements Consumer<CarSaleInfo> {

    private final CarShopClient carShopClient;

    @Override
    public void accept(CarSaleInfo carSaleInfo) {


        carShopClient.addCar(carSaleInfo.getCar(), carSaleInfo.getPrice(), carSaleInfo.getOwnerContacts());
    }
}
