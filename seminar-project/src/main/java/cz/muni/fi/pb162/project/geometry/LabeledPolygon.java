package cz.muni.fi.pb162.project.geometry;

import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class stores vertices under their names in sorted map.
 *
 * @author Renata Urbanova
 */
public final class LabeledPolygon extends SimplePolygon implements Labelable, Sortable {
    private Map<String, Vertex2D> mapOfVertices = new TreeMap<>();

    /**
     * Build LabeledPolygon with the given map
     * @param mapOfVertices representing vertices
     */
    private LabeledPolygon(Map<String, Vertex2D> mapOfVertices) {
        super(mapOfVertices.values().toArray(new Vertex2D[0]));
        this.mapOfVertices = new TreeMap<>(mapOfVertices);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("The negative value of the index.\"");
        }
        List<Vertex2D> list = new ArrayList<>(mapOfVertices.values());
        return list.get(index% mapOfVertices.size());
    }

    @Override
    public int getNumVertices() {
        return mapOfVertices.size();
    }

    @Override
    public Vertex2D getVertex(String label) {
        if (! mapOfVertices.containsKey(label)) {
            throw new IllegalArgumentException("Vertex does not exist.");
        }
        return mapOfVertices.get(label);
    }

    @Override
    public Collection<String> getLabels() {
        List<String> listOfLabels = new ArrayList<>(mapOfVertices.keySet());
        return listOfLabels.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        List<String> listOfSameVertices = new ArrayList<>();
        for (Map.Entry<String, Vertex2D> entry : mapOfVertices.entrySet()) {
            if (entry.getValue().equals(vertex)) {
                listOfSameVertices.add(entry.getKey());
            }
        }
        return listOfSameVertices;
    }

    @Override
    public Collection<Vertex2D> getSortedVertices() {
        List<Vertex2D> vertices = new ArrayList<>(mapOfVertices.values());
        return vertices.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        List<Vertex2D> vertices = new ArrayList<>(mapOfVertices.values());
        return vertices.stream()
                .distinct()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    /**
     * Return a set of vertices that are stored multiple times under names in polygon.
     *
     * @return set of duplicate vertices
     */
    public Collection<Vertex2D> duplicateVertices() {
        List<Vertex2D> vertices = new ArrayList<>(mapOfVertices.values());
        Set<Vertex2D> verticesSet = new HashSet<>();
        Set<Vertex2D> duplicateVertices = new HashSet<>();
        for (Vertex2D vertex : vertices) {
            if (verticesSet.contains(vertex)) {
                duplicateVertices.add(vertex);
            }
            verticesSet.add(vertex);
        }
        return duplicateVertices;
    }

    /**
     * Inner class for building polygon. Implements Buildable interface.
     *
     * @author Renata Urbanova
     */
    public static class Builder implements Buildable {
        private final Map<String, Vertex2D> mapOfBuilder;

        /**
         * Create Builder object.
         */
        public Builder() {
            mapOfBuilder = new TreeMap<>();
        }

        /**
         * Create Builder object and fill parameter.
         *
         * @param map of vertices
         */
        public Builder(Map<String, Vertex2D> map) {
            mapOfBuilder = new TreeMap<>(map);
        }

        /**
         * Add vertex in map under given label.
         *
         * @param label or name of the vertex
         * @param vert vertex
         * @return Builder object
         */
        public Builder addVertex(String label, Vertex2D vert) {
            if (label == null) {
                throw new IllegalArgumentException("Given label is null.");
            }
            if (vert == null) {
                throw new IllegalArgumentException("Given vertex is null.");
            }
            mapOfBuilder.put(label, vert);
            return new Builder(Collections.unmodifiableMap(mapOfBuilder));
        }

        /**
         * Build LabeledPolygon
         *
         * @return LabeledPolygon object
         */
        public LabeledPolygon build() {
            return new LabeledPolygon(Collections.unmodifiableMap(mapOfBuilder));
        }
    }
}
