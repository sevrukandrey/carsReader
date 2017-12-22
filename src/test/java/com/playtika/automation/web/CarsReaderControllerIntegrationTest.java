package com.playtika.automation.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtika.automation.service.CarsReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarsReaderController.class)
public class CarsReaderControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CarsReaderService carService;

    @Test
    public void shouldAddCar() throws Exception {
        when(carService.addCar(constructCar(), 1000, "Andrey")).thenReturn(1L);

        String contentAsString = mockMvc.perform(post("/cars")
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content("{\"brand\": \"ford\",\"model\":\"fiesta\",\"plateNumber\":\"12-22\",\"year\":\"1212\",\"color\":\"green\"}")
            .param("price", "1000")
            .param("ownerContacts", "Andrey"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
            .andReturn()
            .getResponse()
            .getContentAsString();

        verify(carService).addCar(constructCar(), 1000, "Andrey");

        assertThat(contentAsString).isEqualTo("1");
    }
}
