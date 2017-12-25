package com.playtika.automation.domain;

import lombok.*;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Car {
    private String brand;
    private String model;
    private String plateNumber;
    private String color;
    private int year;

    public Car(String brand, String model){
       this.brand = brand;
       this.model = model;
    }
}
