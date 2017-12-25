package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.external.CarShopClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsumeCarSaleInfoTest {

    private CarSaleInfo carSaleInfo;
    @Mock
    private CarShopClient carShopClient;
    private ConsumeCarSaleInfo consumeCarSaleInfo;


    @Before
    public void init() {
        Car car = new Car("Audy", "q1", "aa1010aa", "green", 2010);
        carSaleInfo = new CarSaleInfo(car, "0937746730", 100.0);
        consumeCarSaleInfo = new ConsumeCarSaleInfo(carShopClient);
    }

    @Test
    public void shouldConsumeCarSaleInfo() {

        when(carShopClient.addCar(carSaleInfo.getCar(),
                carSaleInfo.getPrice(),
                carSaleInfo.getOwnerContacts()))
                .thenReturn(1L);

        consumeCarSaleInfo.accept(carSaleInfo);

        verify(carShopClient).addCar(any(Car.class), anyDouble(), anyString());
    }
}