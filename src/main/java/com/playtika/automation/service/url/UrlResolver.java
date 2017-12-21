package com.playtika.automation.service.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface UrlResolver {
    List<String> resolve(String url) throws IOException;
}
