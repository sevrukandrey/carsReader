package com.playtika.automation.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtika.automation.domain.*;
import com.playtika.automation.service.configuration.CarShopClientConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatCode;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CarShopClientConfiguration.class)
@AutoConfigureWireMock
public class CarShopClientTest {

    @Autowired
    CarShopClient carShopClient;

    @Autowired
    protected ObjectMapper objectMapper;

    private Car car;
    private Client client;
    private double price;
    private String ownerContacts;
    private String carInJson;

    @Before
    public void init() {
        car = new Car("Ford", "fiesta", "10-10", "green", 2010);
        client = new Client("Andrey", "Sevruk", "093");
        price = 100;
        ownerContacts = "093774";
        carInJson = "{\"brand\": \"Ford\"," +
            "\"model\":\"fiesta\"," +
            "\"plateNumber\":\"10-10\"," +
            "\"color\":\"green\"," +
            "\"year\":2010}";
    }


    @Test
    public void shouldConsumeDataFromFile() throws IOException {
        stubFor(post(urlEqualTo(format("/cars?price=%s&ownerContacts=%s", price, ownerContacts)))
            .withRequestBody(equalToJson(carInJson))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("2")));

        assertThat(carShopClient.addCar(car, price, ownerContacts)).isEqualTo(2L);
    }

    @Test
    public void shouldNotThrowException() throws IOException {
        stubFor(post(urlEqualTo(format("/cars?price=%s&ownerContacts=%s", price, ownerContacts)))
            .withRequestBody(equalToJson(carInJson))
            .willReturn(aResponse()
                .withStatus(400)
                .withHeader("Content-Type", "application/json")
                .withBody("Bad request")));

        assertThatCode(() -> {
            long resultId = carShopClient.addCar(car, price, ownerContacts);
            assertThat(resultId).isEqualTo(-1);
        }).doesNotThrowAnyException();
    }

    @Test
    public void shouldGetAllCars() throws JsonProcessingException {
        List<CarSaleInfoResponse> expectedCarSaleInfo =
            Collections.singletonList(new CarSaleInfoResponse(1L, car, new SaleInfo("093", 500.0)));

        stubFor(get(urlEqualTo("/cars"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"id\":1,\"car\":{\"brand\":\"Ford\",\"model\":\"fiesta\",\"plateNumber\":\"10-10\",\"color\":\"green\",\"year\":2010}," +
                    "\"saleInfo\":{\"ownerContacts\":\"093\",\"price\":500.0}}]")));

        List<CarSaleInfoResponse> allCars = carShopClient.getAllCars();

        assertThat(allCars).isEqualTo(expectedCarSaleInfo);
    }

    @Test
    public void deleteCar() throws JsonProcessingException {
        long id = 1L;

        stubFor(delete(urlEqualTo(format("/cars/%s", id)))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")));

        carShopClient.deleteCar(id);
    }

    @Test
    public void shouldRejectDeal() throws JsonProcessingException {
        long id = 1L;

        stubFor(post(urlEqualTo(format("/rejectDeal?dealId=%s", id)))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")));

        carShopClient.rejectDeal(id);
    }

    @Test
    public void shouldPutCatToSale() throws JsonProcessingException {
        CarOnSaleRequest carOnSaleRequest = new CarOnSaleRequest(car, client, 500.0);

        stubFor(put(urlEqualTo("/car"))
            .withRequestBody(equalToJson("{\"car\":{\"brand\":\"Ford\",\"model\":\"fiesta\",\"plateNumber\":\"10-10\",\"color\":\"green\",\"year\":2010}," +
                "\"client\":{\"name\":\"Andrey\",\"sureName\":\"Sevruk\",\"phoneNumber\":\"093\"},\"price\":500.0}"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                .withBody("2")));

        assertThat(carShopClient.putCarToSale(carOnSaleRequest)).isEqualTo(2L);
    }

    @Test
    public void shouldChooseBestDeal(){
        long id = 1L;

        stubFor(get(urlEqualTo(format("/bestDeal?advertId=%s", id)))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                .withBody("2")));

        assertThat(carShopClient.chooseBestDeal(id)).isEqualTo(2L);
    }


    @Test
    public void shouldCreateDeal() throws JsonProcessingException {
        long id = 1L;
        DealRequest dealRequest = new DealRequest(client, 500.0);

        stubFor(post(urlEqualTo(format("/deal?advertId=%s", id)))
            .withRequestBody(equalToJson("{\"client\":{\"name\":\"Andrey\",\"sureName\":\"Sevruk\",\"phoneNumber\":\"093\"},\"price\":500.0}"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                .withBody("2")));

        assertThat(carShopClient.createDeal(id, dealRequest)).isEqualTo(2L);
    }

}