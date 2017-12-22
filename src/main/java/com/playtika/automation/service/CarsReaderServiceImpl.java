package com.playtika.automation.service;

import com.playtika.automation.service.url.UrlResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CarsReaderServiceImpl implements CarsReaderService {
    private final UrlResolver urlResolver;
    private final ToCarSaleInfo toCarSaleInfo;
    private final ConsumeCarSaleInfo consumeCarSaleInfo;

    @Override
    public void resolveCarFromFile(String url) throws IOException {
        List<String> linesFromFile = urlResolver.resolve(url);
        linesFromFile.stream().map(toCarSaleInfo).forEach(consumeCarSaleInfo);
    }
}

