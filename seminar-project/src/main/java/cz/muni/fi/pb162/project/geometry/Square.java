package cz.muni.fi.pb162.project.geometry;

/**
 * Class for square representation.
 *
 * @author Renata Urbanova
 */
public class Square extends GeneralRegularPolygon{
    private static final int NUMBER_OF_EDGES = 4;

    /**
     * Create square object with a center and a diameter.
     *
     * @param center of the square
     * @param diameter of the square
     */
    public Square(Vertex2D center, double diameter) {
        super(center, NUMBER_OF_EDGES, diameter / 2);
    }

    /**
     * Create square based on input Circular object.
     * Using object's center and radius.
     *
     * @param object Circular
     */
    public Square(Circular object) {
        this(object.getCenter(), object.getRadius() * 2);
    }

    @Override
    public String toString() {
        return "Square: vertices=" + getVertex(0) + " " + getVertex(1) + " " +
        getVertex(2) + " " + getVertex(3);
    }
}
