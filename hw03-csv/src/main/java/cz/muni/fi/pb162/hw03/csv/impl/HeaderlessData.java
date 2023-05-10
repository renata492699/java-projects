package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * This class implements CsvReader interface. Parses data and save them to List.
 *
 * @author Renata Urbanova
 */
public class HeaderlessData implements CsvReader {
    private List<String> data;
    private String delimiter;
    private int lowestIndex;

    /**
     * Construct HeaderleesData object. Parse data and save to List.
     *
     * @param is InputStream
     * @param delimiter delimiter
     * @param charset charset
     * @throws IOException if length of header is not matching number of columns.
     */
    public HeaderlessData(InputStream is, String delimiter, Charset charset) throws IOException {
        List<String> list = new ArrayList<>();
            InputStreamReader reader = new InputStreamReader(is, charset);
            BufferedReader br = new BufferedReader(reader);
            while (br.ready()) {
                String lineString = br.readLine();
                if (lineString.length() > 0) {
                    list.add(lineString);
                }
            }
            data = list;
            this.delimiter = delimiter;
            lowestIndex = 0;
    }

    @Override
    public List<String> read() {
        if (lowestIndex == data.size()) {
            return null;
        }
        List<String> result = Arrays.asList(data.get(lowestIndex).split(delimiter));
        lowestIndex += 1;
        return result.stream().map(String::strip).collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer consumer) throws IOException {
        List<String> result = read();
        while (result != null) {
            consumer.accept(result);
            result = read();
        }
    }

    @Override
    public void close() throws IOException {

    }
}