package cz.muni.fi.pb162.project.geometry;
import java.util.Arrays;

/**
 * This class represents polygon with vertices stored in array. Extends SimplePolygon abstract class.
 *
 * @author Renata Urbanova
 */

public class ArrayPolygon extends SimplePolygon{
    private final Vertex2D[] arrayOfVertices;

    /**
     * Creates array of polygons of an input argument.
     * @param arrayOfVertices of polygon
     */
    public ArrayPolygon(Vertex2D[] arrayOfVertices) {
        super(arrayOfVertices);
        this.arrayOfVertices = Arrays.copyOf(arrayOfVertices, arrayOfVertices.length);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("The negative value of the index.");
        }
        return arrayOfVertices[(index % arrayOfVertices.length)];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayPolygon that = (ArrayPolygon) o;
        return Arrays.equals(arrayOfVertices, that.arrayOfVertices);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrayOfVertices);
    }

    @Override
    public int getNumVertices() {
        return arrayOfVertices.length;
    }
}
