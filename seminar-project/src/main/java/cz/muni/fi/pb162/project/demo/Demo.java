package cz.muni.fi.pb162.project.demo;
import cz.muni.fi.pb162.project.geometry.RegularOctagon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;


/**
 * Class for running main method.
 *
 * @author Renata Urbanova
 */
public class Demo {

    /**
     * Create octagon with the center [0, 0] and radius 1.
     *
     * @param args command line arguments, will be ignored
     */
    public static void main(String[] args) {
        System.out.println(new RegularOctagon(new Vertex2D(), 1));
    }
}
