package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * This class represents colored polygon.
 *
 * @author Renata Urbanova
 */
public class ColoredPolygon{
    private Color color;
    private final Polygon coloredPolygon;

    /**
     * Add new property color.
     *
     * @param polygon that supposed to be colored
     * @param color of the polygon
     */
    public ColoredPolygon(Polygon polygon, Color color) {
        this.color = color;
        coloredPolygon = polygon;
    }

    public Color getColor() {
        return color;
    }

    public Polygon getPolygon() {
        return coloredPolygon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColoredPolygon that = (ColoredPolygon) o;
        return color == that.color && Objects.equals(coloredPolygon, that.coloredPolygon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, coloredPolygon);
    }
}
