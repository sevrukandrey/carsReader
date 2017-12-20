package com.playtika.automation.service;

import java.io.IOException;
import java.net.MalformedURLException;

public interface CarsReaderService {
    void convertToCars(String url) throws IOException;
}
