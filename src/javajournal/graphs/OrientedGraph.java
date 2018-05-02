package javajournal.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class OrientedGraph<T> implements Graph<T> {

    private HashMap<T, Node<T>> graph;

    public OrientedGraph() {
        graph = new HashMap<>();
    }

    @Override
    public boolean contains(T node) {
        return graph.containsKey(node);
    }

    @Override
    public boolean areAdjacent(T src, T dest) throws NoSuchNodeException {
        Node<T> srcVertex = graph.get(src);
        Node<T> destVertex = graph.get(dest);

        if (srcVertex == null || destVertex == null) {
            throw new NoSuchNodeException();
        }

        return srcVertex.hasNeighbor(destVertex);
    }

    @Override
    public void addVertex(T vertex) {
        Node<T> vertexNode = new Node<>(vertex);
        graph.put(vertex, vertexNode);
    }

    @Override
    public void removeVertex(T vertex) throws NoSuchNodeException {
        Node<T> vertexNode = graph.get(vertex);

        if (vertexNode == null) {
            throw new NoSuchNodeException();
        }

        Iterator<Node<T>> iterator = graph.values().iterator();
        while (iterator.hasNext()) {
            Node<T> possibleLink = iterator.next();
            possibleLink.removeEdgeTo(vertexNode);
        }

        graph.remove(vertex);
    }

    @Override
    public void addEdge(T from, T to, int weight) throws NoSuchNodeException {
        Node<T> fromVertex = graph.get(from);
        Node<T> toVertex = graph.get(to);

        if (fromVertex == null || toVertex == null) {
            throw new NoSuchNodeException();
        }

        Edge<T> edge = new Edge<>(fromVertex, toVertex, weight);
        fromVertex.addEdge(edge);
    }

    @Override
    public void removeEdge(T from, T to) throws NoSuchNodeException {
        Node<T> fromVertex = graph.get(from);
        Node<T> toVertex = graph.get(to);

        if (fromVertex == null || toVertex == null) {
            throw new NoSuchNodeException();
        }

        if (fromVertex.hasNeighbor(toVertex)) {
            fromVertex.removeEdgeTo(toVertex);
        }
    }

    @Override
    public List<T> getNeighborsFor(T vertex) throws NoSuchNodeException {
        if (graph.get(vertex) == null) {
            throw new NoSuchNodeException();
        }

        return graph.get(vertex).getNeighbors();
    }

    @Override
    public List<T> depthSearch(T start) throws NoSuchNodeException {
        if (graph.get(start) == null) {
            throw new NoSuchNodeException();
        }
        List<T> elements = new ArrayList<>();
        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Stack<T> stack = new Stack<>();
        stack.push(start);
        elements.add(start);
        
        while (!stack.empty()) {
            T current = stack.peek();
            T neighbor = null;
            Iterator<T> iterator = getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (!visited.contains(neighbor)) {
                    break;
                }
            }

            if (neighbor != null && !visited.contains(neighbor)) {
                visited.add(neighbor);
                
                elements.add(neighbor);
                stack.push(neighbor);
            } else {
                stack.pop();
            }
        }
        return elements;
    }

    @Override
    public List<T> breathSearch(T start) throws NoSuchNodeException {
        if (graph.get(start) == null) {
            throw new NoSuchNodeException();
        }
        List<T> elements = new ArrayList<>();
        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Queue<T> queue = new ArrayDeque<>();
        queue.add(start);
        elements.add(start);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            T neighbor = null;
            Iterator<T> iterator = getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    elements.add(neighbor);

                    queue.add(neighbor);
                }
            }
        }
        return elements;
    }

    public void topologicalSort() throws NoSuchNodeException {
        Iterator<T> iterator = this.graph.keySet().iterator();
        Collection<T> visited = new ArrayList<>();

        while (iterator.hasNext()) {
            T next = iterator.next();
            //visited.add(next);
            //ordered.add(next);
            if (!visited.contains(next)) {
                depthSearch(next, visited);
            }
        }

        System.out.println("size " + visited.size());

        Collections.reverse((List<?>) visited);

        for (T t : visited) {
            System.out.println(t.toString());
        }

    }

    public void _depthSearch(T start, Collection<T> visited) throws NoSuchNodeException {

    }

    public void depthSearch(T start, Collection<T> visited) throws NoSuchNodeException {

        Stack<T> stack = new Stack<>();
        stack.push(start);

        System.out.println(start);
        while (!stack.empty()) {
            T current = stack.peek();
            T neighbor = null;
            Iterator<T> iterator = getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (!visited.contains(neighbor)) {
                    break;
                }
            }

            if (neighbor != null && !visited.contains(neighbor)) {
                visited.add(neighbor);
                System.out.println(neighbor);

                stack.push(neighbor);
            } else {
                stack.pop();
            }
        }
    }

}
