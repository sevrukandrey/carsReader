package com.playtika.automation.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarSaleInfo {
    private Car car;
    private String ownerContacts;
    private double price;


}
