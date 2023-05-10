package cz.muni.fi.pb162.hw02.impl;

import java.io.IOException;


/**
 * This class is working with given text.
 *
 * @author Renata Urbanova
 */
public class TerminalOperations extends Operations{

    /**
     * Put text in list.
     *
     * @param path of the file
     * @throws IOException when incorrect path
     */
    public TerminalOperations(String path) throws IOException {
        super(path);
    }

    @Override
    public void count() {
        System.out.println(textLines.size());
    }

    @Override
    public void lines() {
        for (String lines : textLines) {
            System.out.println(lines);
        }
    }

    @Override
    public void sizes() {
        for (String s : textLines) {
            System.out.println(s.length() + ": " + s);
        }
    }

    @Override
    public void similar() {
        int theMostSimilar = 100;
        String first = "";
        String second = "";
        for (String s1 : textLines) {
            for (String s2 : textLines) {
                if (s1.equals(s2)) {
                    continue;
                }
                 int distance = LevenshteinDistanceDP.computeLevenshteinDistanceDP(s1, s2);
                 if (distance < theMostSimilar) {
                     theMostSimilar = distance;
                     first = s1;
                     second = s2;
                }
            }
        }
        if (theMostSimilar == 100 || first.equals("") || second.equals("")) {
            System.err.println("Sorry, invalid.");
            return;
        }
        System.out.println("Distance of " + theMostSimilar);
        System.out.println(first + " ~= " + second);
    }
}
