package com.playtika.automation.web;


import com.playtika.automation.service.CarsReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class CarsReaderController {
    private final CarsReaderService service;

    @PostMapping(value = "/cars")
    public void carsWithFile(@RequestParam("url") String url) {
        service.readCar();
    }
}
