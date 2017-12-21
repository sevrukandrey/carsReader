package com.playtika.automation.web;


import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.CarsReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequiredArgsConstructor
public class CarsReaderController {
    private final CarsReaderService service;

    @PostMapping
    public void dataFromFile(@RequestBody String url) throws IOException {
       service.resolveCarFromFile(url);
    }
}
