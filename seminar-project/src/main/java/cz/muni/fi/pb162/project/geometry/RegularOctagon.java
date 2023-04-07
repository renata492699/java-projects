package cz.muni.fi.pb162.project.geometry;

/**
 * This class is extension of GeneralRegularPolygon class.
 *
 * @author Renata Urbanova
 */
public class RegularOctagon extends GeneralRegularPolygon{
    private static final int NUMBER_OF_EDGES = 8;

    /**
     * Create an octagon.
     * @param center of the octagon
     * @param radius of the octagon
     */
    public RegularOctagon(Vertex2D center, double radius) {
        super(center, NUMBER_OF_EDGES, radius);
    }
}
