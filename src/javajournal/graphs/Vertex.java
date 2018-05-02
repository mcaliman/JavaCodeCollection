package javajournal.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex<T> implements Iterable<Vertex<T>> {

    private T info;
    private ArrayList<Edge<T>> neighbors;

    public Vertex(T info) {
        this.info = info;
        neighbors = new ArrayList<Edge<T>>();
    }

    private Edge<T> getEdgeTo(Vertex<T> target) {
        Iterator<Edge<T>> edges = neighbors.iterator();

        while (edges.hasNext()) {
            Edge<T> current = edges.next();
            if (current.dest().equals(target)) {
                return current;
            }
        }

        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vertex<T> other = (Vertex<T>) obj;
        if (info == null) {
            if (other.info != null) {
                return false;
            }
        } else if (!info.equals(other.info)) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<Vertex<T>> iterator() {
        return new VertexIterator<T>(neighbors);
    }

    public void addEdge(Edge<T> edge) {
        if (neighbors.contains(edge)) {
            return;
        } else {
            neighbors.add(edge);
        }
    }

    public boolean hasNeighbor(Vertex<T> neighbor) {
        Iterator<Vertex<T>> iterator = iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(neighbor)) {
                return true;
            }
        }

        return false;
    }

    public void removeEdgeTo(Vertex<T> neighbor) {
        Edge<T> edge = getEdgeTo(neighbor);
        neighbors.remove(edge);
    }

    public List<T> getNeighbors() {
        List<T> neighbors = new ArrayList<>();
        Iterator<Vertex<T>> iterator = iterator();

        while (iterator.hasNext()) {
            neighbors.add(iterator.next().value());
        }

        return neighbors;
    }

    public T value() {
        return info;
    }
}
