package javajournal.graphs;

import java.util.Collection;
import java.util.List;

public interface Graph<T> {

    public boolean contains(T item);

    public void addNode(T item);

    public boolean areAdjacent(T a, T b) throws NoSuchNodeException;

    public void removeNode(T item) throws NoSuchNodeException;

    public void addEdge(T from, T to, int weight) throws NoSuchNodeException;

    public void removeEdge(T from, T to) throws NoSuchNodeException;

    public Collection<T> getNeighbors(T item) throws NoSuchNodeException;

    public List<T> depthSearch(T start) throws NoSuchNodeException;

    public List<T> breathSearch(T start) throws NoSuchNodeException;

    public List<T> topologicalSort() throws NoSuchNodeException;
}
