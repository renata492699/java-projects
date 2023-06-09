package cz.muni.fi.pb162.hw02.impl;

import java.util.Arrays;

/**
 * Class computing Levenshtein distance
 *
 * @author https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
 */
public class LevenshteinDistanceDP {
    static int computeLevenshteinDistanceDP(String str1, String str2) {

        // A 2-D matrix to store previously calculated
        // answers of subproblems in order
        // to obtain the final

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {

                // If str1 is empty, all characters of
                // str2 are inserted into str1, which is of
                // the only possible method of conversion
                // with minimum operations.
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    // find the minimum among three
                    // operations below


                    dp[i][j] = minmEdits(dp[i - 1][j - 1]
                                    + numOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    // check for distinct characters
    // in str1 and str2

    static int numOfReplacement(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    // receives the count of different
    // operations performed and returns the
    // minimum value among them.

    static int minmEdits(int... nums) {
        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
    }
}
