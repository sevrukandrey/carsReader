package com.playtika.automation.domain;

import lombok.Value;

@Value
public class CarOnSaleRequest {
    private Car car;
    private Client client;
    private double price;
}
