package com.playtika.automation.service;

import com.playtika.automation.domain.CarSaleInfo;

import java.io.IOException;
import java.util.List;

public interface CarsReaderService {
    void resolveCarFromFile(String url) throws IOException;
}
