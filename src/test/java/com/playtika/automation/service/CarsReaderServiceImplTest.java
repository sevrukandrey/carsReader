package com.playtika.automation.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CarsReaderServiceImplTest {
    private CarsReaderService service;
    String url = "https://fex.net/load/203511103047/158521824";

    @Before
    public void init() {
        service = new CarsReaderServiceImpl();
    }


}