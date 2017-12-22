package com.playtika.automation.service.external;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.playtika.automation.domain.Car;
import com.playtika.automation.service.configuration.CarShopClientConfiguration;
import com.playtika.automation.web.CarsReaderController;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CarShopClientConfiguration.class)
@AutoConfigureWireMock(port = 2112)
public class CarShopClientTest {

    @Autowired
    CarShopClient carShopClient;

    private Car car;
    private double price;
    private String ownerContacts;

    @Before
    public void init() {
        car = new Car("BMW", "X5", "aa10-20aa", "green", 2010);
        price = 100;
        ownerContacts = "093774";
    }


    @Test
    public void shouldConsumeDataFromFile() throws IOException {
        carShopClient.addCar(car, price, ownerContacts);

        stubFor(post(urlEqualTo("/cars"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json").withBody("1")));
    }


}