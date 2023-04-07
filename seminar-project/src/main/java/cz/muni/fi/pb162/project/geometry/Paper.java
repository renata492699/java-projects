package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This class represents paper and implements Drawable interface.
 *
 * @author Renata Urbanova
 */
public class Paper implements Drawable, PolygonFactory{
    private final Set<ColoredPolygon> paperSet;
    private Color color;
    private static final Color DEFAULT_COLOR  = Color.BLACK;

    /**
     * Create paper and set color to default black.
     */
    public Paper() {
        paperSet = new HashSet<>();
        color = DEFAULT_COLOR;
    }

    /**
     * Create paper and set color to default black.
     *
     * @param drawing is 'saved' on the paper
     */
    public Paper(Drawable drawing) {
        paperSet = new HashSet<>(drawing.getAllDrawnPolygons());
        color = DEFAULT_COLOR;
    }

    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException{
        if (color == Color.WHITE) {
            throw new TransparentColorException("You are trying to draw in white.");
        }
        ColoredPolygon coloredPolygon = new ColoredPolygon(polygon, color);
        paperSet.add(coloredPolygon);
    }

    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        paperSet.remove(polygon);
    }

    @Override
    public void eraseAll() throws EmptyDrawableException{
        if (paperSet.isEmpty()) {
            throw new EmptyDrawableException("The paper is already clean/empty.");
        }
        paperSet.clear();
    }

    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableSet(paperSet);
    }

    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> verticesSet = new HashSet<>();
        for (Iterator<ColoredPolygon> iter = paperSet.iterator(); iter.hasNext();) {
            ColoredPolygon polygon = iter.next();
            for (int index = 0; index < polygon.getPolygon().getNumVertices(); index++) {
                verticesSet.add(polygon.getPolygon().getVertex(index));
            }
        }
        return verticesSet.size();
    }

    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) {
        if (vertices == null) {
            throw new NullPointerException("The given list is null.");
        }
        try {
            return new CollectionPolygon(new ArrayList<>(vertices));
        } catch (IllegalArgumentException ex) {
            List<Vertex2D> cleanList = new ArrayList<>();
            for (Iterator<Vertex2D> iter = vertices.iterator(); iter.hasNext();) {
                Vertex2D vertex = iter.next();
                if (vertex != null) {
                    cleanList.add(vertex);
                }
            }
            return tryToCreatePolygon(cleanList);

        }
    }

    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {
        int count = 0;
        Throwable lastException = null;
        for (Iterator<List <Vertex2D>> iterList = collectionPolygons.iterator(); iterList.hasNext();) {
            List<Vertex2D> polygonVertices = iterList.next();
            try {
                Polygon polygon = tryToCreatePolygon(polygonVertices);
                drawPolygon(polygon);
                count += 1;
            } catch (TransparentColorException ex) {
                changeColor(Color.BLACK);
                lastException = ex;
            } catch (MissingVerticesException ex) {
                System.out.println(ex.getMessage());
                lastException = ex;
            } catch (NullPointerException ex) {
                lastException = ex;
            }
        }
        if (count == 0) {
            throw new EmptyDrawableException("There are no polygon that could be drawn.", lastException);
        }
    }

    /**
     * Return collection of polygons that have the certain color.
     *
     * @param color that we want to filter
     * @return collection of polygons with one color
     */
     public Collection<Polygon> getPolygonsWithColor(Color color) {
        Set<ColoredPolygon> copy = new HashSet<>(paperSet);
         return copy.stream()
                 .filter(p -> p.getColor() == color)
                 .map(ColoredPolygon::getPolygon)
                 .collect(Collectors.toList());
     }
}
