package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * Class for Vertex2D coordinates.
 *
 * @author Renata Urbanova
 */
public class Vertex2D implements Comparable<Vertex2D>{
    private final double x;
    private final double y;

    /**
     * Create vertex with two given parameters.
     *
     * @param x value
     * @param y value
     */
    public Vertex2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create vertex [0, 0].
     */
    public Vertex2D() {
        this(0,0);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "["+ x + ", " + y + "]";
    }

    /**
     * Find the middle of two given Vertex.
     *
     * @param vertex we split
     * @return vertex of the middle
     */
    public Vertex2D createMiddle(Vertex2D vertex) {
        return new Vertex2D((vertex.x + x) / 2, (vertex.y + y) / 2);
    }

    /**
     *
     * @param vertex vertex
     * @return -1 if parameter is null, otherwise return distance between two vertexes
     */
    public double distance(Vertex2D vertex) {
        if (vertex == null) {
            return -1;
        }
        return Math.abs(Math.sqrt((vertex.getX() - x) *  (vertex.getX() - x) +
                Math.pow(vertex.getY() - y, 2)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex2D that = (Vertex2D) o;
        return x == that.getX() && y == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Compare two vertices naturally.
     *
     * @param vertex we are comparing with
     * @return 0 if the vertices are the same, positive number if first vertex > second vertex,
     * negative number if first vertex < second vertex
     */
    public int compareTo(Vertex2D vertex) {
        int xCompare = Double.compare(x, vertex.getX());
        if (xCompare == 0) {
            return Double.compare(y, vertex.getY());
        }
        return xCompare;
    }
}
