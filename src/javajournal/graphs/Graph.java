package javajournal.graphs;

import java.util.Collection;

public interface Graph<T> {

    public boolean contains(T item);

    public void addVertex(T vertex);

    public boolean areAdjacent(T a, T b) throws Exception;

    public void removeVertex(T vertex) throws Exception;

    public void addEdge(T from, T to, int weight) throws Exception;

    public void removeEdge(T from, T to) throws Exception;

    public Collection<T> getNeighborsFor(T vertex) throws Exception;

    public void depthSearch(T start) throws Exception;

    public void breathSearch(T start) throws Exception;
}
