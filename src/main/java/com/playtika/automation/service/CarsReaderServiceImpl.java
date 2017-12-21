package com.playtika.automation.service;

import com.playtika.automation.domain.CarSaleInfo;
import com.playtika.automation.service.external.CarShopClient;
import com.playtika.automation.domain.Car;
import com.playtika.automation.service.url.UrlResolver;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@NoArgsConstructor
public class CarsReaderServiceImpl implements CarsReaderService {

    @Autowired
    private UrlResolver urlResolver;

    @Autowired
    private ToCarSaleInfo toCarSaleInfo;


    @Override
    public void resolveCarFromFile(String url) throws IOException {
        List<String> linesFromFile = urlResolver.resolve(url);
        linesFromFile.stream().map(toCarSaleInfo::apply).
    }

//
//    @Autowired
//    CarShopClient carShopClient;
//
//    private String savedFileLocation = "F:/test99.txt";
//    private double price;
//    private String ownerPhone;
//
//    @Override
//    public void addCarToShop(String fileUrl) throws IOException {
//
//        downloadFile(fileUrl);
//        ---- > Class List<String> array 2. array.strem-- > to CarSakeInfo Object ()
//        String[] lines = splitDownloadTextToLine();
//        convertLinesToCarsForSale(lines);
//
//    }
//
//    private void convertLinesToCarsForSale(String[] lines) {
//        List<CarSaleInfo> cars = new ArrayList<>();
//        Arrays.stream(lines)
//            .map(line -> line.split(","))
//            .forEachOrdered(elements -> {
//                Car car = new Car();
//                car.setBrand(elements[0]);
//                car.setModel(elements[1]);
//                car.setColor(elements[2]);
//                car.setYear(Integer.parseInt(elements[3]));
//                car.setPlateNumber(elements[4]);
//                price = Double.parseDouble(elements[6]);
//                ownerPhone = elements[5];
//                carShopClient.addCar(car, price, ownerPhone);
//            });
//    }
//
//    private String[] splitDownloadTextToLine() throws IOException {
//        String text = Files.readAllLines(Paths.get(savedFileLocation)).toString().replaceAll("[\\[|\\]]", "");
//        return text.split(" ");
//    }
//
//    private void downloadFile(String fileUrl) throws IOException {
//        File destination = new File(savedFileLocation);
//        URL url = new URL(fileUrl);
//        copyURLToFile(url, destination);
//    }


}

