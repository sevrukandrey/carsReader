package com.playtika.automation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarSaleInfo {
    private Car car;
    private String ownerContacts;
    private double price;
}
