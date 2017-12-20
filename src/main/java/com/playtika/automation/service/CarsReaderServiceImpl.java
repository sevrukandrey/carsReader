package com.playtika.automation.service;

import com.playtika.automation.domain.CarSaleInfo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.io.FileUtils.*;

@Service
public class CarsReaderServiceImpl implements CarsReaderService {

    private String savedFileLocation = "F:/test99.txt";
    @Override
    public void convertToCars(String fileUrl) throws IOException {

        downloadFile(fileUrl);
        String[] lines = splitDownloadTextToLine();
        convertLinesToCarsForSale(lines);

    }

    private void convertLinesToCarsForSale(String[] lines) {
        List<CarSaleInfo> cars = new ArrayList<>();
        for (String line : lines) {
            String[] splitLine = line.split(",");

            for (String aSplitLine : splitLine) {
                CarSaleInfo carSaleInfo = new CarSaleInfo();
                carSaleInfo.setBrand(splitLine[0]);
                carSaleInfo.setBrand(splitLine[1]);
                carSaleInfo.setModel(splitLine[2]);
                carSaleInfo.setYear(Integer.parseInt(splitLine[3]));
                carSaleInfo.setPlateNumber(splitLine[4]);
                cars.add(carSaleInfo);
            }


        }

        System.out.println(cars);
    }

    private String[] splitDownloadTextToLine() throws IOException {
        String text = Files.readAllLines(Paths.get(savedFileLocation)).toString().replaceAll("[\\[|\\]]", "");
       String [] array = text.split(" ");


        return array;
    }

    private void downloadFile(String fileUrl) throws IOException {
        File destination = new File(savedFileLocation);
        URL url = new URL(fileUrl);
        copyURLToFile(url, destination);
    }
}

