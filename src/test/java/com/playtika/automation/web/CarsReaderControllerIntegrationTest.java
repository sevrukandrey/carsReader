package com.playtika.automation.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtika.automation.domain.Car;
import com.playtika.automation.service.CarsReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarsReaderController.class)
public class CarsReaderControllerIntegrationTest {

    String url = "https://fex.net/load/203511103047/158521824";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CarsReaderService carService;

    @Test
    public void shouldAddCar() throws Exception {
        doNothing().when(carService).resolveCarFromFile(url);

        mockMvc.perform(post("/file")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content("https://fex.net/load/203511103047/158521824"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(carService).resolveCarFromFile(url);

    }
}
