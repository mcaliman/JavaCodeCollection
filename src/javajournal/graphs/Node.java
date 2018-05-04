package javajournal.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node<T> implements Iterable<Node<T>> {

    private T data;
    private List<Edge<T>> neighbors;

    public Node(T data) {
        this.data = data;
        this.neighbors = new ArrayList<Edge<T>>();
    }

    private Edge<T> getEdgeTo(Node<T> target) {
        Iterator<Edge<T>> edges = neighbors.iterator();
        while (edges.hasNext()) {
            Edge<T> current = edges.next();
            if (current.getTarget().equals(target)) {
                return current;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
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
        Node<T> other = (Node<T>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new NodeIterator<>(neighbors);
    }

    public void addEdge(Edge<T> edge) {
        if (!neighbors.contains(edge)) {
            neighbors.add(edge);
        } 
    }

    public boolean hasNeighbor(Node<T> neighbor) {
        Iterator<Node<T>> it = iterator();
        while (it.hasNext()) {
            Node node = it.next();
            if (node.equals(neighbor)) {
                return true;
            }
        }
        return false;
    }
        
    public void removeEdgeTo(Node<T> neighbor) {
        Edge<T> edge = getEdgeTo(neighbor);
        neighbors.remove(edge);
    }

    public List<T> getNeighbors() {
        List<T> neighborsList = new ArrayList<>();
        Iterator<Node<T>> it = iterator();
        while (it.hasNext()) {
            neighborsList.add(it.next().value());
        }
        return neighborsList;
    }

    public T value() {
        return data;
    }
}
