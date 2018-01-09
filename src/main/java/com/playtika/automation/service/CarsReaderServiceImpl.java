package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.external.CarShopClient;
import com.playtika.automation.service.url.UrlResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CarsReaderServiceImpl implements CarsReaderService {
    private final UrlResolver urlResolver;
    private final CarShopClient carShopClient;

    @Override
    public void resolveCarFromFile(String url) throws IOException {
        List<String> linesFromFile = urlResolver.resolve(url);
        linesFromFile
                .stream()
                .map(this::toCarSaleInfo)
                .forEach(this::processCar);
    }

    private CarSaleInfo toCarSaleInfo(String line) throws IndexOutOfBoundsException{
        String[] split = line.split(",");

        Car car = new Car(split[0], split[1], split[4], split[2], Integer.parseInt(split[3]));
        double price = Double.parseDouble(split[6]);
        String ownerContacts = split[5];

        return new CarSaleInfo(car, ownerContacts, price);
    }

    private void processCar(CarSaleInfo carSaleInfo) {
        carShopClient.addCar(carSaleInfo.getCar(), carSaleInfo.getPrice(), carSaleInfo.getOwnerContacts());
    }
}



