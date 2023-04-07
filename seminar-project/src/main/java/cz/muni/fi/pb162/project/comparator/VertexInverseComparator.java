package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * Comparator sort vertices by descending.
 * First sort by X coordinates and in case of a match, sort Y by descending.
 *
 * @author Renata Urbanova
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {
    @Override
    public int compare(Vertex2D o1, Vertex2D o2) {
        return -1 * o1.compareTo(o2);
    }
}
