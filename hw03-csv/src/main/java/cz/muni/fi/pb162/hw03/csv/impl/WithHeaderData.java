package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvReader;
import cz.muni.fi.pb162.hw03.csv.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;


/**
 * This class implements CsvReader interface. Parses data with header, ie. saves data to Map.
 *
 * @author Renata Urbanova
 */
public class WithHeaderData implements CsvReader {
    private Map<String, String> data;
    private final String delimiter;
    private int lowestIndex;

    /**
     * Construct WithHeaderData object. Parse data and save to Map.
     *
     * @param is InputStream
     * @param delimiter delimiter
     * @param charset charset
     * @throws IOException if length of header is not matching number of columns.
     */
    public WithHeaderData(InputStream is, String delimiter, Charset charset) throws IOException {
        Map<String, String> map = new HashMap<>();
        InputStreamReader reader = new InputStreamReader(is, charset);
        BufferedReader br = new BufferedReader(reader);
        boolean header = true;
        Integer lineNumber = 1;
        while (br.ready()) {
            String line = br.readLine();
            if (header) {
                map.put("header", line);
                header = false;
            } else {
                map.put(lineNumber.toString(), line);
                lineNumber += 1;
            }
        }
        data = map;
        this.delimiter = delimiter;
        lowestIndex = 1;
    }

    @Override
    public Map<String, String> read() throws IOException{
        Map<String, String> result = new HashMap<>();
        if (lowestIndex == data.keySet().size()) {
            return null;
        }
        String[] header = data.get("header").split(delimiter);
        String[] values = data.get(Integer.toString(lowestIndex)).split(delimiter);
        if (header.length != values.length) {
            lowestIndex += 1;
            throw new IOException(Messages.INVALID_FORMAT);
        }
        int index = 0;
        for (String s : header) {
            result.put(s.strip(), values[index].strip());
            index += 1;
        }
        lowestIndex += 1;
        return result;
    }

    @Override
    public void forEach(Consumer consumer) throws IOException {
        Map<String, String> result = read();
        while (result != null) {
            consumer.accept(result);
            result = read();
        }
    }

    @Override
    public void close() throws IOException {

    }
}
