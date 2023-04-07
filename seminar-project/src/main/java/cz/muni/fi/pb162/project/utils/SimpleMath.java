package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Polygon;

/**
 * This class implements methods max and min of X or Y coordinates.
 *
 * @author Renata Urbanova
 */
public class SimpleMath {

    /**
     * Static method for maximum of coordinates.
     *
     * @param polygon object
     * @return the maximum of X coordinates
     */
    public static double maxX(Polygon polygon) {
        double maxX = polygon.getVertex(0).getX();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getX() > maxX) {
                maxX = polygon.getVertex(i).getX();
            }
        }
        return maxX;
    }

    /**
     * Static method for maximum of coordinates.
     *
     * @param polygon object
     * @return the maximum of Y coordinates
     */
    public static double maxY(Polygon polygon) {
        double maxY = polygon.getVertex(0).getY();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getY() > maxY) {
                maxY = polygon.getVertex(i).getY();
            }
        }
        return maxY;
    }

    /**
     * Static method for minimum of coordinates.
     *
     * @param polygon object
     * @return the minimum of X coordinates
     */
    public static double minX(Polygon polygon) {
        double minX = polygon.getVertex(0).getX();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getX() < minX) {
                minX = polygon.getVertex(i).getX();
            }
        }
        return minX;
    }

    /**
     * Static method for minimum of coordinates.
     *
     * @param polygon object
     * @return the minimum of Y coordinates
     */
    public static double minY(Polygon polygon) {
        double minY = polygon.getVertex(0).getY();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getY() < minY) {
                minY = polygon.getVertex(i).getY();
            }
        }
        return minY;
    }
}