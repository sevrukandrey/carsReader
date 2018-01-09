package com.playtika.automation.service.url;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UrlResolverImpl implements UrlResolver {

    @Override
    public List<String> resolve(String url) throws IOException {
        log.debug("Start resolving line from url:{}", url);

        InputStream inputStream = new URL(url).openStream();
        List<String> dataFromUrl = IOUtils.readLines(inputStream, "UTF-8");
        inputStream.close();
        return dataFromUrl;
    }
}
