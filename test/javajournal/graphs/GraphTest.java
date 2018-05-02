package javajournal.graphs;

import java.util.Collection;
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
        graph.addVertex(item);
        boolean result = graph.contains(item);
        assertEquals(true, result);
    }

    @Test
    public void testAddVertex() {
        System.out.println("addVertex");
        Entity item = new Entity(1);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addVertex(item);
        assertEquals(true, graph.contains(item));
    }

    @Test
    public void testAreAdjacent() throws Exception {
        System.out.println("areAdjacent");
        Entity a = new Entity(1);
        Entity b = new Entity(2);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b, 0);
        boolean result = graph.areAdjacent(a, b);
        assertEquals(true, result);
    }

    
    @Test
    public void testRemoveVertex() throws Exception {
        System.out.println("removeVertex");
        Entity a = new Entity(1);
        Graph<Entity> graph = new OrientedGraph<>();
        graph.addVertex(a);
        assertEquals(true, graph.contains(a));
        graph.removeVertex(a);
        assertEquals(false, graph.contains(a));
    }

    /**
     * Test of addEdge method, of class Graph.
     */
    //@Test
    public void testAddEdge() throws Exception {
        System.out.println("addEdge");
        Object from = null;
        Object to = null;
        int weight = 0;
        Graph instance = new OrientedGraph();
        instance.addEdge(from, to, weight);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    //@Test
    public void testRemoveEdge() throws Exception {
        System.out.println("removeEdge");
        Object from = null;
        Object to = null;
        Graph instance = new OrientedGraph();
        instance.removeEdge(from, to);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNeighborsFor method, of class Graph.
     */
    //@Test
    public void testGetNeighborsFor() throws Exception {
        System.out.println("getNeighborsFor");
        Object vertex = null;
        Graph instance = new OrientedGraph();
        Collection expResult = null;
        Collection result = instance.getNeighborsFor(vertex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of depthSearch method, of class Graph.
     */
    //@Test
    public void testDepthSearch() throws Exception {
        System.out.println("depthSearch");
        Object start = null;
        Graph instance = new OrientedGraph();
        instance.depthSearch(start);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of breathSearch method, of class Graph.
     */
    //@Test
    public void testBreathSearch() throws Exception {
        System.out.println("breathSearch");
        Object start = null;
        Graph instance = new OrientedGraph();
        instance.breathSearch(start);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
