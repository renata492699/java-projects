package cz.muni.fi.pb162.hw01;

import java.util.Objects;

/**
 * Utility methods available to be used in your solution
 */
public final class Utils {

    private Utils() {
        // Intentionally made private to prevent instantiation
    }

    /**
     * Finds the index of given element in array
     * @param array array to be searched
     * @param element element to be search for
     * @param <T> generic array type parameter
     * @return index of given element or -1 if element is not in the array
     */
    public static <T> int searchIndex(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of given element in char array
     * @param array array to be searched
     * @param element element to be search for
     * @return index of given element or -1 if element is not in the array
     */
    public static int searchIndex(char[] array, char element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Throws an error with given message
     * @param message error message
     */
    public static void error(String message) {
        throw new RuntimeException(message);
    }
}
