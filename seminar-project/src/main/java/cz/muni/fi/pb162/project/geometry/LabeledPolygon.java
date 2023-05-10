package cz.muni.fi.pb162.project.geometry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
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
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.SortedMap;

/**
 * This class stores vertices under their names in sorted map.
 *
 * @author Renata Urbanova
 */
public final class LabeledPolygon extends SimplePolygon implements Labelable, Sortable, PolygonWritable {
    private final Map<String, Vertex2D> mapOfVertices;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

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
        SortedSet<Vertex2D> sorted = new TreeSet<>(comparator);
        sorted.addAll(mapOfVertices.values());
        return sorted;
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
     * This
     * @param label label
     * @param vert vertex
     */
    public void addVertex(String label, Vertex2D vert) {
        this.mapOfVertices.put(label, vert);
    }

    /**
     * Inner class for building polygon. Implements Buildable interface.
     *
     * @author Renata Urbanova
     */
    public static class Builder implements Buildable, PolygonReadable{
        private final SortedMap<String, Vertex2D> mapOfBuilder = new TreeMap<>();

        @Override
        public Builder addVertex(String label, Vertex2D vert) {
            if (label == null) {
                throw new IllegalArgumentException("Given label is null.");
            }
            if (vert == null) {
                throw new IllegalArgumentException("Given vertex is null.");
            }
            mapOfBuilder.put(label, vert);
            return this;
        }

        @Override
        public LabeledPolygon build() {
            return new LabeledPolygon(Collections.unmodifiableMap(mapOfBuilder));
        }

        @Override
        public PolygonReadable read(InputStream is) throws IOException {
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            while (br.ready()) {
                String line = br.readLine();
                String[] split = line.split(" ");
                if (split.length >= 3) {
                    try {
                        Vertex2D vertex = new Vertex2D(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
                        String name = "";
                        for (int i = split.length - 2; i < split.length; i++) {
                            name = name + (split[i]);
                            if (i < split.length - 1) {
                                name = name + " ";
                            }
                        }
                        addVertex(name, vertex);
                    } catch (NumberFormatException ex) {
                        throw new IOException("Invalid format of vertices.");
                    }
                } else {
                    throw new IOException("Invalid format.");
                }
            }
            return this;
        }

        @Override
        public PolygonReadable read(File file) throws IOException {
            try (InputStream input = new FileInputStream(file)) {
                read(input);
                return this;
            }
        }
    }

    @Override
    public void write(OutputStream os) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);
        for (Map.Entry<String, Vertex2D> entry : mapOfVertices.entrySet()) {
            Vertex2D vertex = entry.getValue();
            String name = entry.getKey();
            bw.write(vertex.getX() + " " + vertex.getY() + " " + name);
            bw.newLine();
        }
        bw.flush();
    }

    @Override
    public void write(File file) throws IOException {
        try (OutputStream output = new FileOutputStream(file)) {
            write(output);
        }
    }


    /**
     * Write a map in JSON format to the output stream.
     * @param os output stream
     * @throws IOException in invalid format case
     */
    public void writeJson(OutputStream os) throws IOException{
        String result = GSON.toJson(mapOfVertices);
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(result);
        bw.flush();
    }
}
