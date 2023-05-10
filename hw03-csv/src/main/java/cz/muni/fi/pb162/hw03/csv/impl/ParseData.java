package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvParser;
import cz.muni.fi.pb162.hw03.csv.CsvReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


/**
 * This class implements CsvParser.
 *
 * @author Renata Urbanova
 */
public class ParseData implements CsvParser {
    private String delimiter = CsvParser.DEFAULT_DELIMITER;
    private Charset charset = CsvParser.DEFAULT_CHARSET;

    /**
     * Construct object with default delimiter(",") and charset(utf8).
     */
    public ParseData() {

    }

    /**
     * Construct object with given delimiter and charset.
     *
     * @param delimiter use for parsing
     * @param charset parameter
     */
    public ParseData(String delimiter, Charset charset) {
        this.delimiter = delimiter;
        this.charset = charset;
    }

    /**
     * Construct object with default charset(utf8) and given delimiter.
     *
     * @param delimiter use for parsing
     */
    public ParseData(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Construct object with default delimiter(",").
     *
     * @param charset parameter
     */
    public ParseData(Charset charset) {
        this.charset = charset;
    }

    @Override
    public CsvReader<List<String>> open(Path path) throws IOException {
        File input = new File(path.toString());

        try (InputStream is = new FileInputStream(input)) {
            return new HeaderlessData(is, delimiter, charset);
        }
    }

    @Override
    public CsvReader<List<String>> open(InputStream is) throws IOException {
        CsvReader<List<String>> result = new HeaderlessData(is, delimiter, charset);
        is.close();
        return result;
    }

    @Override
    public CsvReader<Map<String, String>> openWithHeader(Path path) throws IOException {
        File input = new File(path.toString());

        try (InputStream is = new FileInputStream(input)) {
            return new WithHeaderData(is, delimiter, charset);
        }
    }

    @Override
    public CsvReader<Map<String, String>> openWithHeader(InputStream is) throws IOException {
        CsvReader<Map<String, String>> result = new WithHeaderData(is, delimiter, charset);
        is.close();
        return result;
    }

    @Override
    public List<Map<String, String>> readAllWithHeader(Path path) throws IOException {
        CsvReader<Map<String, String>> withHeader = openWithHeader(path);
        Map<String, String> data = withHeader.read();
        List<Map<String, String>> result = new ArrayList<>();
        while (data != null) {
            result.add(data);
            data = withHeader.read();
        }
        return result;
    }

    @Override
    public List<List<String>> readAll(Path path) throws IOException {
        CsvReader<List<String>> headerless = open(path);
        List<List<String>> result = new ArrayList<>();
        List<String> data = headerless.read();
        while (data != null) {
            result.add(data);
            data = headerless.read();
        }
        return result;
    }

}

