package cz.muni.fi.pb162.project.geometry;

/**
 * This enum class represents colors.
 *
 * @author Renata Urbanova
 */
public enum Color {
    WHITE, RED, GREEN, BLUE, BLACK, YELLOW, PINK, ORANGE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
