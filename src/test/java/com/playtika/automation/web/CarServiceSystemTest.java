package com.playtika.automation.web;

import com.playtika.qa.carsclient.CarShopClient;
import com.playtika.qa.carsclient.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.playtika.qa.carsclient.domain.DealStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CarServiceSystemTest {
    @Autowired
    CarShopClient carShopClient;

    @Test
    public void shouldChooseBestDeal() {

        CarOnSaleRequest carOnSaleRequest = constructCarOnSaleRequest();
        long advertId = carShopClient.putCarToSale(carOnSaleRequest);
        CarResponse carByAdvertId = carShopClient.getCarByAdvertId(advertId);
        assertThat(carByAdvertId).isNotNull();

        DealRequest dealRequestWithHigherPrice = constructDealRequest(1000.0, "123");
        long dealIdWithHigherPrice = carShopClient.createDeal(advertId, dealRequestWithHigherPrice);

        DealRequest dealRequestWithLowerPrice = constructDealRequest(900.0, "321");
        long dealIdWithLowerPrice = carShopClient.createDeal(advertId, dealRequestWithLowerPrice);

        DealRequest dealRequestWillRejected = constructDealRequest(10000, "555");
        long dealIdShouldBeRejected = carShopClient.createDeal(advertId, dealRequestWillRejected);

        carShopClient.rejectDeal(dealIdShouldBeRejected);
        DealResponse dealResponse = carShopClient.dealById(dealIdShouldBeRejected);
        assertThat(dealResponse.getStatus()).isEqualTo(REJECTED);

        long dealId = carShopClient.chooseBestDeal(advertId);
        assertThat(dealId).isEqualTo(dealIdWithHigherPrice);

        assertThat(carShopClient.dealById(dealIdWithHigherPrice).getStatus()).isEqualTo(APPROVED);
        assertThat(carShopClient.dealById(dealIdWithLowerPrice).getStatus()).isEqualTo(REJECTED);
    }

    private DealRequest constructDealRequest(double price, String phoneNumber) {

        return new DealRequest(new Client("Andrey", "First", phoneNumber), price);
    }

    private CarOnSaleRequest constructCarOnSaleRequest() {
        return new CarOnSaleRequest(new CarRequest("ford", "fiesta", "0" + 1, "green", 2016),
            new Client("Andrey", "Sevruk", "093"), 999.0);
    }
}
