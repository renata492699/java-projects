package cz.muni.fi.pb162.project.geometry;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class stores vertices of n-gon in collection.
 * Also extends SimplePolygon class.
 *
 * @author Renata Urbanova
 */
public class CollectionPolygon extends SimplePolygon{
    private final List<Vertex2D> collectionOfVertices;

    /**
     * Create simple polygon object from array of vertices
     *
     * @param arrayOfVertices of a polygon
     */
    public CollectionPolygon(Vertex2D[] arrayOfVertices) {
        super(arrayOfVertices);
        List<Vertex2D> vertices = Arrays.asList(arrayOfVertices);
        collectionOfVertices = List.copyOf(vertices);
    }

    /**
     * Create a polygon objects from list of vertices
     *
     * @param listOfVertices of a polygon
     */
    public CollectionPolygon(List<Vertex2D> listOfVertices) {
        super(listOfVertices.toArray(new Vertex2D[0]));
        collectionOfVertices = new ArrayList<>(listOfVertices);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("The negative value of the index.");
        }
        return collectionOfVertices.get(index % collectionOfVertices.size());
    }

    @Override
    public int getNumVertices() {
        return collectionOfVertices.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionPolygon that = (CollectionPolygon) o;
        return Objects.equals(collectionOfVertices, that.collectionOfVertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionOfVertices);
    }

    /**
     * Method returns a new polygon without the leftmost vertices.
     * The original polygon remains unchanged.
     *
     * @return null if polygon no longer contains any vertices after removing.
     */
    public CollectionPolygon withoutLeftmostVertices() {
        List<Vertex2D> newArrayList = new ArrayList<>(collectionOfVertices);
        double leftMost = newArrayList.get(0).getX();
        for (Iterator<Vertex2D> iter = collectionOfVertices.iterator(); iter.hasNext();) {
            Vertex2D vertex = iter.next();
            if (vertex.getX() < leftMost) {
                leftMost = vertex.getX();
            }
        }
        for (Iterator<Vertex2D> iter = collectionOfVertices.iterator(); iter.hasNext();) {
            Vertex2D vertex = iter.next();
            if (vertex.getX() == leftMost) {
                newArrayList.remove(collectionOfVertices.indexOf(vertex));
            }
        }
        if (newArrayList.isEmpty()) {
            return null;
        }
        Vertex2D[] arrayOfVertices = newArrayList.toArray(new Vertex2D[0]);
        return new CollectionPolygon(arrayOfVertices);
    }

}
