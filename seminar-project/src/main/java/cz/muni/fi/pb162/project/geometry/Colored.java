package cz.muni.fi.pb162.project.geometry;

/**
 *Interface for Colors.
 *
 * @author Renata Urbanova
 */
public interface Colored {

    /**
     * Return a color of the object.
     *
     * @return color
     */
    Color getColor();

    /**
     * Set the color of the object.
     *
     * @param newColor of the object
     */
    void setColor(Color newColor);
}
