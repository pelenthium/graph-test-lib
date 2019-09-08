package test.simple.graph.lib;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Graph<T> {

    private boolean direction = false;

    private Map<T, List<T>> heads = new HashMap<>();

    public Graph() {
    }

    public Graph(boolean direction) {
        this.direction = direction;
    }

    public void addVertex(T vertex) {
        heads.put(vertex, new CopyOnWriteArrayList<>());
    }

    public void addEdge(T start, T dest) {
        if (heads.containsKey(start)) {
            heads.get(start).add(dest);
        }
        if (!direction && heads.containsKey(dest)) {
            heads.get(dest).add(start);
        }
    }

    public List<T> getPath(T start, T dest) {
        List<T> path = new ArrayList<>();
        dfs(start, dest, path, new HashMap<>());
        return path;
    }

    private boolean dfs(T start, T dest, List<T> path, Map<T, Color> visited) {
        List<T> siblings = heads.getOrDefault(start, new ArrayList<>());
        visited.put(start, Color.GREY);
        path.add(start);
        boolean found = false;
        for (T item : siblings) {
            if (visited.getOrDefault(item, Color.WHITE) == Color.WHITE) {
                if (dest.equals(item)) {
                    path.add(item);
                    return true;
                }
                found = found || dfs(item, dest, path, visited);
            }
        }
        if (!found) {
            path.remove(start);
        }
        visited.put(start, Color.BLACK);
        return found;
    }

    enum Color {
        WHITE, GREY, BLACK
    }
}