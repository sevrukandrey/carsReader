package com.playtika.automation.domain;

import lombok.Value;

@Value
public class DealRequest {
    private Client client;
    private double price;
}
