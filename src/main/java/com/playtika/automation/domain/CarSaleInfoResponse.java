package com.playtika.automation.domain;

import lombok.Value;

@Value
public class CarSaleInfoResponse {
    private long id;
    private Car car;
    private SaleInfo saleInfo;
}
