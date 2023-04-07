package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.utils.SimpleMath;

/**
 * Abstract class for simple polygons. Means general irregular closed n-gons without intersecting edges.
 * Class also implements Polygon interface.
 *
 * @author Renata Urbanova
 */
public abstract class SimplePolygon implements Polygon{

    /**
     * Check if array of polygons is valid
     *
     * @param arrayOfVertices of polygon
     */

    public SimplePolygon(Vertex2D[] arrayOfVertices){
        if (arrayOfVertices == null) {
            throw new IllegalArgumentException("The given array is null.");
        }
        if (arrayOfVertices.length < 3) {
        throw new MissingVerticesException("The array has less than three vertices.");
        }
        for (Vertex2D arrayOfVertex : arrayOfVertices) {
            if (arrayOfVertex == null) {
                throw new IllegalArgumentException("The given array contains null.");
            }
        }
    }

    @Override
    public double getHeight() {
        return SimpleMath.maxY(this) - SimpleMath.minY(this);
    }

    @Override
    public double getWidth() {
        return SimpleMath.maxX(this) - SimpleMath.minX(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Polygon: vertices =");
        for (int i = 0; i < getNumVertices(); i++) {
            result.append(" ");
            result.append(getVertex(i));
        }
        return result.toString();
    }

    @Override
    public abstract int getNumVertices();

    @Override
    public abstract Vertex2D getVertex(int index);
}
