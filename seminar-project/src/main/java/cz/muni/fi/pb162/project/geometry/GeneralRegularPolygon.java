package cz.muni.fi.pb162.project.geometry;

/**
 * This is superclass for all regular polygons.
 * Implements RegularPolygon and Colored interfaces.
 *
 * @author Renata Urbanova
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored{
    private Color color;
    private final Vertex2D center;
    private final int numberOfEdges;
    private final double radius;
    private static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * Create a regular polygon with default color black.
     *
     * @param center of the polygon
     * @param numberOfEdges of the polygon
     * @param radius of the polygon
     */
    public GeneralRegularPolygon(Vertex2D center, int numberOfEdges, double radius) {
        this.center = center;
        this.numberOfEdges = numberOfEdges;
        this.radius = radius;
        this.color = DEFAULT_COLOR;
    }

    @Override
    public int getNumEdges() {
        return numberOfEdges;
    }

    @Override
    public double getEdgeLength() {
        return 2 * radius * Math.sin(Math.PI / numberOfEdges);
    }

    @Override
    public Vertex2D getVertex(int index) {
        return new Vertex2D(center.getX() -
                radius * Math.cos(index * 2 * Math.PI / numberOfEdges),
                center.getY() - radius *
                        Math.sin(index * 2 * Math.PI / numberOfEdges));
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Vertex2D getCenter() {
        return center;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color newColor) {
        color = newColor;
    }

    @Override
    public String toString() {
        return numberOfEdges + "-gon: center=" + center +
                ", radius=" + radius + ", color=" + color;
    }
}

