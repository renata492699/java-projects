package cz.muni.fi.pb162.project.geometry;

/**
 * Class for triangle representation. This class extends ArrayPolygon class.
 *
 * @author Renata Urbanová
 */
public class Triangle extends ArrayPolygon{
    private final Triangle[] arrayOfTriangles;
    private static final double TOLERANCE = 0.001;

    /**
     * Create array of vertices and empty array of triangles.
     *
     * @param vertex1 1st vertex
     * @param vertex2 2nd vertex
     * @param vertex3 3th vertex
     */
    public Triangle(Vertex2D vertex1, Vertex2D  vertex2, Vertex2D vertex3) {
        super(new Vertex2D[] {vertex1,vertex2,vertex3});
        arrayOfTriangles = new Triangle[] {null, null, null};
    }

    /**
     * Create new triangle and divide it into sub-triangles.
     * Division is controlled by third parameter.
     *
     * @param vertex1 1st vertex
     * @param vertex2 2nd vertex
     * @param vertex3 3th vertex
     * @param depth of division
     */
    public Triangle(Vertex2D vertex1, Vertex2D vertex2, Vertex2D vertex3, int depth) {
        this(vertex1, vertex2, vertex3);
        divide(depth);
    }

    /**
     * Return string in format:
     * Triangle: vertices=[x1, y1] [x2, y2] [x3, y3]
     *
     * @return string of vertices of triangle
     */
    @Override
    public String toString() {
        return "Triangle: vertices=" + getVertex(0) + " " +
                getVertex(1) + " " + getVertex(2);
    }

    /**
     * Divide the triangle to three.
     * Save the individual triangle to array of triangles.
     *
     * @return if triangle was already divided
     */
    public boolean divide() {
        if (isDivided()) {
            return false;
        }
        Vertex2D abMid = getVertex(0).createMiddle(getVertex(1));
        Vertex2D bcMid = getVertex(1).createMiddle(getVertex(2));
        Vertex2D acMid = getVertex(2).createMiddle(getVertex(0));
        arrayOfTriangles[0] = new Triangle(getVertex(0), abMid, acMid);
        arrayOfTriangles[1] = new Triangle(abMid, getVertex(1), bcMid);
        arrayOfTriangles[2] = new Triangle(acMid, bcMid, getVertex(2));
        return true;
    }

    public boolean isDivided() {
        return arrayOfTriangles[0] != null;
    }

    /**
     * Get on of the sub-triangle of the triangle according to index.
     *
     * @param index in range from 0 to 2
     * @return null if triangle was not divided yet
     */
    public Triangle getSubTriangle(int index) {
        if (!isDivided() || index < 0 || index > 2) {
            return null;
        }
        return arrayOfTriangles[index];
    }

    /**
     * Triangle is equilateral if all of its sides are same length or
     * the difference is smaller than default tolerance
     *
     * @return if triangle is equilateral
     */
    public boolean isEquilateral() {
        double ab = getVertex(0).distance(getVertex(1));
        double bc = getVertex(1).distance(getVertex(2));
        double ca = getVertex(2).distance(getVertex(0));
        return Math.abs(ab - bc) < TOLERANCE && Math.abs(bc - ca) < TOLERANCE;
    }

    /**
     * Divide triangle in sub-triangle.
     * If depth is 0, there is no division.
     * If depth is 1, we divide triangle into sub-triangles once.
     *
     * @param depth of division
     */
    public void divide(int depth) {
        if (depth <= 0) {
            return;
        }
        divide();
        getSubTriangle(0).divide(depth - 1);
        getSubTriangle(1).divide(depth - 1);
        getSubTriangle(2).divide(depth - 1);
    }
}
