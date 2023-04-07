package cz.muni.fi.pb162.project.geometry;

/**
 * This class builds a Snowman. The high of the snowman is controlled
 * by constant NUMBER_OF_OBJECTS.
 *
 * @author Renata Urbanova
 */
public class Snowman {
    public static final int NUMBER_OF_OBJECTS = 3;
    private static final double FACTOR = 0.8;
    private final RegularPolygon[] snowBalls = new RegularPolygon[NUMBER_OF_OBJECTS];

    /**
     * This method builds snowman.
     * Decreasing each object as we are build up.
     * High of the snowman is controlled by constant NUMBER_OF_OBJECTS.
     * Each of the polygon is saved in array snowBalls.
     *
     * @param lowerObject is the base of the Snowman
     * @param factor of which the balls are decreasing,
     *               if the factor is out of range (0, 1>, use default factor
     */
    public Snowman(RegularPolygon lowerObject, double factor) {
        if (factor <= 0 || factor > 1) {
            factor = FACTOR;
        }
        buildSnowman(lowerObject, factor);
    }

    /**
     * This method helps to build the snowman.
     * Fills the array Snowballs.
     *
     * @param lowerObject the bottom object
     * @param factor of decreasing
     */
    private void buildSnowman(RegularPolygon lowerObject, double factor) {
        double radius = lowerObject.getRadius();
        double centerX = lowerObject.getCenter().getX();
        double centerY = lowerObject.getCenter().getY();
        int numOfEdges = lowerObject.getNumEdges();
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            RegularPolygon object = new GeneralRegularPolygon(new Vertex2D(centerX, centerY), numOfEdges, radius);
            snowBalls[i] = object;
            centerY += radius;
            radius *= factor;
            centerY += radius;
        }
    }

    public RegularPolygon[] getBalls() {
        return snowBalls;
    }
}
