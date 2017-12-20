package com.playtika.automation.domain;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class CarSaleInfo {
    private  String brand;
    private  String model;
    private  String plateNumber;
    private  String color;
    private  int year;
    private String ownerContacts;
    private double price;
}
