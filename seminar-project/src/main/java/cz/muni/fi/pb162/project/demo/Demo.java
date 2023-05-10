package cz.muni.fi.pb162.project.demo;
import cz.muni.fi.pb162.project.geometry.LabeledPolygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.io.File;
import java.io.IOException;

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
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .read(new File("polygon-ok.txt"))
                .build();
        polygon.addVertex("vrchol x", new Vertex2D(123, 456));
        polygon.writeJson(System.out);
        System.out.println("Hello World!");
    }
}
