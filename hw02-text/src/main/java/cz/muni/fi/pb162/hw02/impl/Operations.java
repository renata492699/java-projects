package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FileLoader;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Super class for operations.
 *
 * @author Renata Urbanova
 */
public abstract class Operations {
    protected List<String> textLines;

    /**
     * From path take a file with text and save it as a list by lines.
     *
     * @param path of a file
     * @throws IOException if path is incorrect
     */
    public Operations(String path) throws IOException{
        FileLoader lines = new FileLoader();
        textLines = new ArrayList<>(lines.loadAsLines(path));
    }

    /**
     * Sort lines by natural ordering.
     */
    public void sort() {
        Collections.sort(textLines);
    }

    /**
     * Filter duplicate lines;
     */
    public void duplicates() {
        Set<String> linesSet = new HashSet<>(textLines);
        List<String> linesList = new ArrayList<>(textLines);
        for (String s : linesSet) {
            linesList.remove(s);
        }
        textLines = linesList;
    }

    /**
     * Filter unique lines.
     */
    public void unique() {
        Set<String> linesSet = new HashSet<>(textLines);
        textLines = new ArrayList<>(linesSet);
    }

    public List<String> getTextLines(){
        return textLines;
    }

    /**
     * Print lines;
     */
    abstract void lines();

    /**
     * Count lines.
     */
    abstract void count();

    /**
     * Counts the characters for each line. (Excluding line separators).
     */
    abstract void sizes();

    /**
     * Lists pairs of most similar (distinct) lines according to Levenshtein distance.
     */
    abstract void similar();
}
