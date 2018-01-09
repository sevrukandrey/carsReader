package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.external.CarShopClient;
import com.playtika.automation.service.url.UrlResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarsReaderServiceImplTest {

    private CarsReaderService carsReaderService;

    @Mock
    private UrlResolver urlResolver;

    @Mock
    private CarShopClient carShopClient;

    @Before
    public void init() throws IOException {
        carsReaderService = new CarsReaderServiceImpl(urlResolver, carShopClient);
    }

    @Test
    public void shouldComposeCarSaleInfoFromEachLine() throws IOException {
        List<String> linesFromUrl = createLines();

        CarSaleInfo carSaleInfo = createCarSaleInfo();

        when(urlResolver.resolve("text")).thenReturn(linesFromUrl);

        carsReaderService.resolveCarFromFile("text");

        verify(carShopClient, times(2)).addCar(carSaleInfo.getCar(),
                carSaleInfo.getPrice(),
                carSaleInfo.getOwnerContacts());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowException() throws IOException {
        List<String> notFullLine = Collections.singletonList("Audy,q1,green,2010,aa1010aa,0937746730");

        when(urlResolver.resolve("text")).thenReturn(notFullLine);

        carsReaderService.resolveCarFromFile("text");
    }

    private CarSaleInfo createCarSaleInfo() {
        Car car = new Car("Audy", "q1", "aa1010aa", "green", 2010);
        return new CarSaleInfo(car, "0937746730", 100.0);
    }

    private List<String> createLines() {
        List<String> linesFromUrl = new ArrayList<>();
        linesFromUrl.add("Audy,q1,green,2010,aa1010aa,0937746730,100");
        linesFromUrl.add("Audy,q1,green,2010,aa1010aa,0937746730,100");
        return linesFromUrl;
    }

}