package test.simple.graph.lib;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    @Test
    public void testEmptyGraph() {
        Graph<String> graph = new Graph<>();
        assertEquals(0, graph.getPath("A", "B").size());
        assertEquals(0, graph.getPath("B", "A").size());
    }

    @Test
    public void testEmptyPath() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(0, graph.getPath("A", "B").size());
        assertEquals(0, graph.getPath("B", "A").size());
    }

    @Test
    public void testSimplePath() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        List<String> pathAB = graph.getPath("A", "B");
        assertEquals(2, pathAB.size());
        assertEquals(Arrays.asList("A", "B"), pathAB);
        List<String> pathBA = graph.getPath("B", "A");
        assertEquals(2, pathBA.size());
        assertEquals(Arrays.asList("B", "A"), pathBA);
    }

    @Test
    public void testTreeVertexPath() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        List<String> pathAB = graph.getPath("A", "C");
        assertEquals(3, pathAB.size());
        assertEquals(Arrays.asList("A", "B", "C"), pathAB);
    }

    @Test
    public void testComplexGraphPath() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("C1");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("E1");
        graph.addVertex("F");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("B", "C1");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("D", "E1");
        graph.addEdge("E", "F");
        List<String> pathAF = graph.getPath("A", "F");
        assertEquals(6, pathAF.size());
        assertEquals(Arrays.asList("A", "B", "C", "D", "E", "F"), pathAF);
    }

    @Test
    public void testDirectionGraphPath() {
        Graph<String> graph = new Graph<>(true);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        List<String> pathAB = graph.getPath("A", "B");
        assertEquals(2, pathAB.size());
        List<String> pathBA = graph.getPath("B", "A");
        assertEquals(0, pathBA.size());
    }

    @Test
    public void testStartNotFirstVertexPath() {
        Graph<String> graph = new Graph<>(true);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("C1");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("B", "C1");
        graph.addEdge("C", "D");
        List<String> pathBD = graph.getPath("B", "D");
        assertEquals(3, pathBD.size());
        assertEquals(Arrays.asList("B", "C", "D"), pathBD);
    }

    @Test
    public void testFindNotLastVertexPath() {
        Graph<String> graph = new Graph<>(true);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("C1");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("B", "C1");
        graph.addEdge("C", "D");
        List<String> pathBC1 = graph.getPath("B", "C1");
        assertEquals(2, pathBC1.size());
        assertEquals(Arrays.asList("B", "C1"), pathBC1);
    }

    @Test
    public void testFindFirstVertex() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        List<String> pathAA = graph.getPath("A", "A");
        assertEquals(0, pathAA.size());
    }

    @Test
    public void testCircleGraph () {
        Graph<String> graph = new Graph<>(true);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        List<String> pathAC = graph.getPath("A", "C");
        assertEquals(3, pathAC.size());
        assertEquals(Arrays.asList("A", "B", "C"), pathAC);
    }
}