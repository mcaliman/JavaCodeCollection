package javajournal.graphs;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
        graph.addEdge(a, b, 0);
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
    public void testAddEdge() throws Exception {
        System.out.println("addEdge");
        Entity from = new Entity(1);
        Entity to = new Entity(2);
        int weight = 0;
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(from);
        graph.addNode(to);
        graph.addEdge(from, to, weight);
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
        graph.addEdge(from, to, 0);
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
        graph.addEdge(a, b, 0);
        graph.addEdge(a, c, 0);
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

        graph.addEdge(e0, e1, 0);
        graph.addEdge(e0, e2, 0);
        graph.addEdge(e1, e2, 0);
        graph.addEdge(e2, e0, 0);
        graph.addEdge(e2, e3, 0);
        graph.addEdge(e3, e3, 0);
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

        graph.addEdge(e0, e1, 0);
        graph.addEdge(e0, e2, 0);
        graph.addEdge(e1, e2, 0);
        graph.addEdge(e2, e0, 0);
        graph.addEdge(e2, e3, 0);
        graph.addEdge(e3, e3, 0);

        List<Entity> list = graph.breathSearch(e2);
        //expected 2 0 3 1
        boolean eq = list.equals(Arrays.asList(e2, e0, e3, e1));
        assertEquals(true, eq);

    }

    //FIX!
    //@Test
    public void testTopologicalSort() throws Exception {
        System.out.println("topologicalSort");
        Entity e0 = new Entity(0);
        Entity e1 = new Entity(1);
        Entity e2 = new Entity(2);
        Entity e3 = new Entity(3);
        Entity e4 = new Entity(4);
        Entity e5 = new Entity(5);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addNode(e0);
        graph.addNode(e1);
        graph.addNode(e2);
        graph.addNode(e3);
        graph.addNode(e4);
        graph.addNode(e5);

        graph.addEdge(e5, e2, 0);
        graph.addEdge(e5, e0, 0);
        graph.addEdge(e4, e0, 0);
        graph.addEdge(e4, e1, 0);
        graph.addEdge(e2, e3, 0);
        graph.addEdge(e3, e1, 0);

        //Following is a Topological Sort of the given graph 5 4 2 3 1 0
        List<Entity> list = graph.topologicalSort();
        for (Entity entity : list) {
            System.out.print(entity.toString()+" > ");
        }
        
        boolean eq = list.equals(Arrays.asList(e5, e4, e2, e3, e1, e0));
        assertEquals(true, eq);
    }
}
