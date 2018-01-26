package com.playtika.automation.service.url;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UrlResolverImplTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private UrlResolver urlResolver;

    @Before
    public void init() throws IOException {
        urlResolver = new UrlResolverImpl();
    }

    @Test
    public void shouldProcessUrlLineByLineToStringList() throws IOException {
        List<String> expected = addLines();

        String fileLocation = createFileWithData(expected);

        List<String> resolve = urlResolver.resolve(fileLocation);

        assertThat(resolve).hasSize(2);
        assertThat(resolve).isEqualTo(expected);
    }

    @Test
    public void shouldReturnEmptyListIfDownloadedFileIsEmpty() throws IOException {
        String emptyFileLocation = createEmptyFile();

        List<String> resolve = urlResolver.resolve(emptyFileLocation);

        assertThat(resolve).isEmpty();
    }

    private String createEmptyFile() throws IOException {
        File emptyFile = createFileWithData(new ArrayList<>(), "testEmpty.txt");
        return getFileLocation(emptyFile);
    }

    public File createFileWithData(List<String> expected, String name) throws IOException {
        File tempFile = tempFolder.newFile(name);
        FileUtils.writeLines(tempFile, expected);
        return tempFile;
    }

    private String getFileLocation(File file) throws MalformedURLException {
        return file.toURI().toURL().toString();
    }

    private String createFileWithData(List<String> expected) throws IOException {
        File file = createFileWithData(expected, "test.txt");
        return getFileLocation(file);
    }

    private List<String> addLines() {
        List<String> expected = new ArrayList<>();
        expected.add("Audy,q1,green,2010,aa1010aa,0937746730,100");
        expected.add("Bmw,x5,black,2011,aa1050aa,0937746731,200");
        return expected;
    }
}