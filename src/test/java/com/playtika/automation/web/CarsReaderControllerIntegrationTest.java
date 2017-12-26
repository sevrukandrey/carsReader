package com.playtika.automation.web;

import com.playtika.automation.domain.Car;
import com.playtika.automation.service.CarsReaderService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarsReaderController.class)
public class CarsReaderControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @MockBean
    private CarsReaderService service;

    private String fileLocation;

    @Before
    public void init() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("Audy,q1,green,2010,aa1010aa,0937746730,100");
        File file = createFileWithData(expected, "test.txt");
        fileLocation = getFileLocation(file);
        Car car = new Car("Audy", "q1", "aa11aa", "green", 2010);
    }

    @Test
    public void shouldAddCar() throws Exception {

        mockMvc.perform(post("/file")
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(fileLocation))
            .andExpect(status().isOk());

        verify(service).resolveCarFromFile(fileLocation);
    }

    public File createFileWithData(List<String> expected, String name) throws IOException {
        File tempFile = tempFolder.newFile(name);
        FileUtils.writeLines(tempFile, expected);
        return tempFile;
    }

    private String getFileLocation(File file) throws MalformedURLException {
        return file.toURI().toURL().toString();
    }
}
