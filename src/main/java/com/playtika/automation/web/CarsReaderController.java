package com.playtika.automation.web;

import com.playtika.automation.service.CarsReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CarsReaderController {

    private final CarsReaderService service;

    @PostMapping(value = "/file")
    public void consumeDataFromFile(@RequestBody String url) throws IOException {
        service.resolveCarFromFile(url);
    }
}
