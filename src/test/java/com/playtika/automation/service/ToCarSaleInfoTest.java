package com.playtika.automation.service;

import com.playtika.automation.domain.Car;
import com.playtika.automation.domain.CarSaleInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ToCarSaleInfoTest {

    private CarSaleInfo carSaleInfo;
    private ToCarSaleInfo toCarSaleInfo;

    @Before
    public void init() {
        Car car = new Car("Audy", "q1", "aa1010aa", "green", 2010);
        carSaleInfo = new CarSaleInfo(car, "0937746730", 100.0);
        toCarSaleInfo = new ToCarSaleInfo();
    }


    @Test
    public void shouldCreateCarSaleInfoFromLine() {
        String line = "Audy,q1,green,2010,aa1010aa,0937746730,100";

        CarSaleInfo resultedCarSaleInfo = toCarSaleInfo.apply(line);

        assertThat(resultedCarSaleInfo).isEqualTo(carSaleInfo);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldReturnEmptyCarSaleInfoIfInformationInFileIsNotFull() {
        String line = "Audy";

        assertThat(toCarSaleInfo.apply(line)).isEqualTo(new CarSaleInfo());
    }

}