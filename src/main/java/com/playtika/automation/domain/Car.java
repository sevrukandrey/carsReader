package com.playtika.automation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Car {
    private String brand;
    private String model;
    private String plateNumber;
    private String color;
    private int year;
}
