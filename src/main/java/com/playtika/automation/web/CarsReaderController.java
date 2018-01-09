package com.playtika.automation.web;

import com.playtika.automation.service.CarsReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CarsReaderController {

    private final CarsReaderService service;

    @PostMapping(value = "/file")
    public void consumeDataFromFile(@RequestBody @Valid String url) throws IOException {
        service.resolveCarFromFile(url);
    }
}
