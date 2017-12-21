package com.playtika.automation.service.url;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UrlResolverImpl implements UrlResolver {
    @Override
    public List<String> resolve(String url) throws IOException {
        InputStream is = new URL(url).openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.toList());
    }
}
