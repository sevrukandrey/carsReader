package com.playtika.automation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CarSaleInfo {
    private Car car;
    private String ownerContacts;
    private double price;
}
