package javajournal.graphs;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class OrientedGraph<T> implements Graph<T> {

    private HashMap<T, Vertex<T>> graph;

    public OrientedGraph() {
        graph = new HashMap<>();
    }

    @Override
    public boolean contains(T vertex) {
        return graph.containsKey(vertex);
    }

    @Override
    public boolean areAdjacent(T src, T dest) throws NoSuchVertexException {
        Vertex<T> srcVertex = graph.get(src);
        Vertex<T> destVertex = graph.get(dest);

        if (srcVertex == null || destVertex == null) {
            throw new NoSuchVertexException();
        }

        return srcVertex.hasNeighbor(destVertex);
    }

    @Override
    public void addVertex(T vertex) {
        Vertex<T> vertexNode = new Vertex<>(vertex);
        graph.put(vertex, vertexNode);
    }

    @Override
    public void removeVertex(T vertex) throws NoSuchVertexException {
        Vertex<T> vertexNode = graph.get(vertex);

        if (vertexNode == null) {
            throw new NoSuchVertexException();
        }

        Iterator<Vertex<T>> iterator = graph.values().iterator();
        while (iterator.hasNext()) {
            Vertex<T> possibleLink = iterator.next();
            possibleLink.removeEdgeTo(vertexNode);
        }

        graph.remove(vertex);
    }

    @Override
    public void addEdge(T from, T to, int weight) throws NoSuchVertexException {
        Vertex<T> fromVertex = graph.get(from);
        Vertex<T> toVertex = graph.get(to);

        if (fromVertex == null || toVertex == null) {
            throw new NoSuchVertexException();
        }

        Edge<T> edge = new Edge<>(fromVertex, toVertex, weight);
        fromVertex.addEdge(edge);
    }

    @Override
    public void removeEdge(T from, T to) throws NoSuchVertexException {
        Vertex<T> fromVertex = graph.get(from);
        Vertex<T> toVertex = graph.get(to);

        if (fromVertex == null || toVertex == null) {
            throw new NoSuchVertexException();
        }

        if (fromVertex.hasNeighbor(toVertex)) {
            fromVertex.removeEdgeTo(toVertex);
        }
    }

    @Override
    public List<T> getNeighborsFor(T vertex) throws NoSuchVertexException {
        if (graph.get(vertex) == null) {
            throw new NoSuchVertexException();
        }

        return graph.get(vertex).getNeighbors();
    }

    @Override
    public void depthSearch(T start) throws NoSuchVertexException {
        if (graph.get(start) == null) {
            throw new NoSuchVertexException();
        }

        Collection<T> visited = new HashSet<>();
        visited.add(start);

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

    @Override
    public void breathSearch(T start) throws NoSuchVertexException {
        if (graph.get(start) == null) {
            throw new NoSuchVertexException();
        }

        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Queue<T> queue = new ArrayDeque<>();
        queue.add(start);

        System.out.println(start);
        while (!queue.isEmpty()) {
            T current = queue.remove();
            T neighbor = null;
            Iterator<T> iterator = getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    System.out.println(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

}
