package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.url.UrlResolver;
import com.playtika.qa.carsclient.CarShopClient;
import com.playtika.qa.carsclient.domain.CarRequest;
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

    private CarSaleInfo toCarSaleInfo(String line) throws IndexOutOfBoundsException {
        String[] split = line.split(",");

        Car car = new Car(split[0], split[1], split[4], split[2], Integer.parseInt(split[3]));
        double price = Double.parseDouble(split[6]);
        String ownerContacts = split[5];

        return new CarSaleInfo(car, ownerContacts, price);
    }

    private void processCar(CarSaleInfo carSaleInfo) {
        CarRequest carRequest = constructCarRequest(carSaleInfo);
        carShopClient.addCar(carRequest, carSaleInfo.getPrice(),
            carSaleInfo.getOwnerContacts());
    }

    private CarRequest constructCarRequest(CarSaleInfo carSaleInfo) {
        return new CarRequest(carSaleInfo.getCar().getBrand(), carSaleInfo.getCar().getModel(),
            carSaleInfo.getCar().getPlateNumber(),
            carSaleInfo.getCar().getColor(),
            carSaleInfo.getCar().getYear());

    }
}



