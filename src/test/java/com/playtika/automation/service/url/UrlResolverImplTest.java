package com.playtika.automation.service.url;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlResolverImplTest {

    private UrlResolver urlResolver;

    private String url = "https://fex.net/load/203511103047/158521824";
    private String emptyFile = "https://fex.net/load/125139486500/162118566";

    private List<String> expected ;
    @Before
    public void init() {
        urlResolver = new UrlResolverImpl();
        expected = new ArrayList<>();
        expected.add("Audy,q1,green,2010,aa1010aa,0937746730,100");
        expected.add("Bmw,x5,black,2011,aa1050aa,0937746731,200");
        expected.add("Ford,q1,red,2012,aa1040aa,0937746732,300");
        expected.add("Citroen,c4,white,2013,aa1030aa,0937746733,400");
        expected.add("Kia,q1,white,2015,aa1020aa,0937746734,500");
    }
    @Test
    public void shouldProcessUrlLineByLineToStringList() throws IOException {
        List<String> resolve = urlResolver.resolve(url);

        assertThat(resolve).hasSize(5);
        assertThat(resolve).isEqualTo(expected);
    }

    @Test
    public void shouldReturnEmptyListIfDownloadedFileIsEmpty() throws IOException {
        List<String> resolve = urlResolver.resolve(emptyFile);
        assertThat(resolve).isEmpty();
    }

}