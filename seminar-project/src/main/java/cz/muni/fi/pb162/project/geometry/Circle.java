package cz.muni.fi.pb162.project.geometry;

/**
 * Class for a circle. A color of the circle is red by default.
 *
 * @author Renata Urbanova
 */
public class Circle extends GeneralRegularPolygon{
    private static final double DEFAULT_RADIUS = 1;
    private static final int MAXIMUM_INTEGER = Integer.MAX_VALUE;
    private static final Color DEFAULT_COLOR = Color.RED;

    /**
     * Create a new circle with given centre and radius.
     *
     * @param center of the circle
     * @param radius of the circle
     */
    public Circle(Vertex2D center, double radius) {
        super(center, MAXIMUM_INTEGER, radius);
        setColor(DEFAULT_COLOR);
    }

    /**
     * Create a circle with default radius (1) and with the centre [0,0].
     */
    public Circle() {
        this(new Vertex2D(), DEFAULT_RADIUS);
    }

    @Override
    public String toString() {
        return "Circle: center=" + getCenter() + ", radius=" + getRadius();
    }

    @Override
    public double getEdgeLength() {
        return 0;
    }
}