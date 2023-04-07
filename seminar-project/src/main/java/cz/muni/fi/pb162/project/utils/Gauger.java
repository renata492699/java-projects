package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;

/**
 * This class prints info about width and height of an object.
 *
 * @author Renata Urbanova
 */
public class Gauger {

    /**
     * Print info about height and width of the object.
     *
     * @param object Measurable
     */
    public static void printMeasurement(Measurable object) {
        System.out.println("Width: " + object.getWidth());
        System.out.println("Height: " + object.getHeight());
    }

    /**
     * Print height and width info but also info about vertices of the triangle.
     *
     * @param triangle object
     */
    public static void printMeasurement(Triangle triangle) {
        System.out.println(triangle.toString());
        printMeasurement((Measurable) triangle);
    }
}
