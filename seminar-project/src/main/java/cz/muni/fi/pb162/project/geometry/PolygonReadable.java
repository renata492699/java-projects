package cz.muni.fi.pb162.project.geometry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for data import.
 * <p>
 * Data is in the format <code>x y name</code>.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface PolygonReadable {

    /**
     * Read polygon data from input stream.
     *
     * @param is input stream
     * @return the object reading the data
     * @throws IOException on read error
     */
    PolygonReadable read(InputStream is) throws IOException;

    /**
     * Read polygon data from file.
     *
     * @param file input file
     * @return the object reading the data
     * @throws IOException on read error
     */
    PolygonReadable read(File file) throws IOException;

    /**
     * Add vertex in map under given label.
     *
     * @param label or name of the vertex
     * @param vert vertex
     * @return Builder object
     */
    Buildable<Object> addVertex(String label, Vertex2D vert);

    /**
     * Build object.
     *
      * @return polygon
     */
    LabeledPolygon build();
}
