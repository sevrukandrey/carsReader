package com.playtika.automation.service;

import com.playtika.automation.domain.Car;

import java.util.List;

public interface CarsReaderService {
    List<Car> readCar(String url);
}
