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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.String.format;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CarShopClientConfiguration.class)
@AutoConfigureWireMock(port = 8080)
public class CarShopClientTest {

    @Autowired
    CarShopClient carShopClient;

    private Car car;
    private double price;
    private String ownerContacts;

    @Before
    public void init() {
        car = new Car("Ford", "fiesta", "10-10", "green", 2010);
        price = 100;
        ownerContacts = "093774";
    }


    @Test
    public void shouldConsumeDataFromFile() throws IOException {
        stubFor(post(urlEqualTo(format("/cars?price=%s&ownerContacts=%s", price, ownerContacts)))
                .withRequestBody(equalToJson(
                        "{\"brand\": \"Ford\"," +
                                "\"model\":\"fiesta\"," +
                                "\"plateNumber\":\"10-10\"," +
                                "\"color\":\"green\"," +
                                "\"year\":2010}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("2")));

        assertThat(carShopClient.addCar(car, price, ownerContacts)).isEqualTo(2L);
    }

    @Test
    public void shouldReturnBadRequest() throws IOException {
        stubFor(post(urlEqualTo(format("/cars?price=%s&ownerContacts=%s", price, ownerContacts)))
                .withRequestBody(equalToJson(
                        "{\"brand\": \"Ford\"," +
                                "\"model\":\"fiesta\"," +
                                "\"plateNumber\":\"10-10\"," +
                                "\"color\":\"green\"," +
                                "\"year\":2010}"))
                .willReturn(aResponse().withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("Bad request")));

        carShopClient.addCar(car, price, ownerContacts);
    }


}