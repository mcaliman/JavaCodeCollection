package javajournal.graphs;

import java.util.Arrays;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.List;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    private class Entity {

        private Integer id;

        public Entity(Integer id) {
            this.id = id;
        }

        public Entity() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id.toString();
        }

    }

    public GraphTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContains() {
        System.out.println("contains");
        Entity item = new Entity(1);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(item);
        boolean result = graph.contains(item);
        assertEquals(true, result);
    }

    @Test
    public void testAddVertex() {
        System.out.println("addVertex");
        Entity item = new Entity(1);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(item);
        assertEquals(true, graph.contains(item));
    }

    @Test
    public void testAreAdjacent() throws Exception {
        System.out.println("areAdjacent");
        Entity a = new Entity(1);
        Entity b = new Entity(2);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addEdge(a, b);
        boolean result = graph.areAdjacent(a, b);
        assertEquals(true, result);
    }

    @Test
    public void testRemoveVertex() throws Exception {
        System.out.println("removeVertex");
        Entity a = new Entity(1);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(a);
        assertEquals(true, graph.contains(a));
        graph.removeNode(a);
        assertEquals(false, graph.contains(a));
    }

    @Test
    public void testEdges() throws Exception {
        System.out.println("edges");
        Entity a = new Entity(1);
        Entity b = new Entity(2);
        Entity c = new Entity(3);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        List<Edge<Entity>> edges = graph.edges();
        for (Edge<Entity> edge : edges) {
            System.out.println(edge);
        }
        boolean containsAll = edges.containsAll(Arrays.asList(
                new Edge<Entity>(new Node(a), new Node(b)),
                new Edge<Entity>(new Node(a), new Node(c))
        )
        );
        assertEquals(true, containsAll);

    }

    @Test
    public void testIncomingEdges() throws NoSuchNodeException {
        System.out.println("incomingEdges");
        Entity e0 = new Entity(0);
        Entity e1 = new Entity(1);
        Entity e2 = new Entity(2);
        Entity e3 = new Entity(3);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(e0);
        graph.addNode(e1);
        graph.addNode(e2);
        graph.addNode(e3);
        graph.addEdge(e1, e2);
        graph.addEdge(e1, e3);
        graph.addEdge(e0, e1);
        List<Edge<Entity>> edges = graph.incomingEdges(new Node(e1));
        for (Edge<Entity> edge : edges) {
            System.out.println(edge);
        }
        boolean containsAll = edges.containsAll(Arrays.asList(
                new Edge<Entity>(new Node(e0), new Node(e1))
        )
        );
        assertEquals(true, containsAll);
    }

    @Test
    public void testOutgoingEdges() throws NoSuchNodeException {
        System.out.println("outgoingEdges");
        Entity e0 = new Entity(0);
        Entity e1 = new Entity(1);
        Entity e2 = new Entity(2);
        Entity e3 = new Entity(3);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(e0);
        graph.addNode(e1);
        graph.addNode(e2);
        graph.addNode(e3);
        graph.addEdge(e1, e2);
        graph.addEdge(e1, e3);
        graph.addEdge(e0, e1);
        List<Edge<Entity>> edges = graph.outgoingEdges(new Node(e1));
        for (Edge<Entity> edge : edges) {
            System.out.println(edge);
        }
        boolean containsAll = edges.containsAll(Arrays.asList(
                new Edge<Entity>(new Node(e1), new Node(e2)),
                new Edge<Entity>(new Node(e1), new Node(e3))
        )
        );
        assertEquals(true, containsAll);
    }

    @Test
    public void testAddEdge() throws Exception {
        System.out.println("addEdge");
        Entity from = new Entity(1);
        Entity to = new Entity(2);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(from);
        graph.addNode(to);
        graph.addEdge(from, to);
        assertEquals(true, graph.areAdjacent(from, to));
    }

    @Test
    public void testRemoveEdge() throws Exception {
        System.out.println("removeEdge");
        Entity from = new Entity(1);
        Entity to = new Entity(2);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(from);
        graph.addNode(to);
        graph.addEdge(from, to);
        graph.removeEdge(from, to);
    }

    @Test
    public void testGetNeighborsFor() throws Exception {
        System.out.println("getNeighborsFor");
        Entity a = new Entity(1);
        Entity b = new Entity(2);
        Entity c = new Entity(3);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        Collection<Entity> neighbors = graph.getNeighbors(a);
        boolean containsAll = neighbors.containsAll(Arrays.asList(b, c));
        assertEquals(true, containsAll);
    }

    @Test
    public void testDepthSearch() throws Exception {
        System.out.println("depthSearch");
        Entity e0 = new Entity(0);
        Entity e1 = new Entity(1);
        Entity e2 = new Entity(2);
        Entity e3 = new Entity(3);

        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(e0);
        graph.addNode(e1);
        graph.addNode(e2);
        graph.addNode(e3);

        graph.addEdge(e0, e1);
        graph.addEdge(e0, e2);
        graph.addEdge(e1, e2);
        graph.addEdge(e2, e0);
        graph.addEdge(e2, e3);
        graph.addEdge(e3, e3);
        List<Entity> list = graph.depthSearch(e2);
        //expected  2 0 1 3
        boolean eq = list.equals(Arrays.asList(e2, e0, e1, e3));
        assertEquals(true, eq);
    }

    @Test
    public void testBreathSearch() throws Exception {
        System.out.println("breathSearch");
        Entity e0 = new Entity(0);
        Entity e1 = new Entity(1);
        Entity e2 = new Entity(2);
        Entity e3 = new Entity(3);

        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(e0);
        graph.addNode(e1);
        graph.addNode(e2);
        graph.addNode(e3);

        graph.addEdge(e0, e1);
        graph.addEdge(e0, e2);
        graph.addEdge(e1, e2);
        graph.addEdge(e2, e0);
        graph.addEdge(e2, e3);
        graph.addEdge(e3, e3);

        List<Entity> list = graph.breathSearch(e2);
        //expected 2 0 3 1
        boolean eq = list.equals(Arrays.asList(e2, e0, e3, e1));
        assertEquals(true, eq);

    }

 
    private class Expr {

        private String lvalue;
        private String rvalue;

        public Expr(String lvalue, String rvalue) {
            this.lvalue = lvalue;
            this.rvalue = rvalue;
        }

        @Override
        public String toString() {
            return lvalue + " = " + rvalue;
        }

    }

    @Test
    public void testKahnTopSort() throws Exception {
        System.out.println("topologicalSort");
        Expr B1 = new Expr("B1", "A1");
        Expr A1 = new Expr("A1", "10");
        Expr A2 = new Expr("A2", "A1 + 2");
        Expr A3 = new Expr("A3", "A1 + A2");
        Graph<Expr> graph = new OrientedGraph<>();
        graph.addNode(B1);
        graph.addNode(A1);
        graph.addNode(A2);
        graph.addNode(A3);

        graph.addEdge(A1, A2);
        graph.addEdge(A1, B1);
        graph.addEdge(A1, A3);

        graph.addEdge(A2, A3);

        //Topological Sort of the given graph 
        List<Expr> list = graph.kahnTopSort();
        for (Expr expr : list) {
            System.out.println(expr.toString());
        }
        List<Expr> exp = Arrays.asList(A1, A2, B1, A3);
        boolean eq = list.equals(exp);
        assertEquals(true, eq);
    }

}
