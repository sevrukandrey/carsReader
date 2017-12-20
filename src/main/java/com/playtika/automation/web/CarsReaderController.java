package com.playtika.automation.web;


import com.playtika.automation.service.CarsReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class CarsReaderController {
    private final CarsReaderService service;

    @PostMapping(value = "/cars")
    public void carsWithFile(@RequestBody String url) throws IOException {
        service.convertToCars(url);
    }
}
