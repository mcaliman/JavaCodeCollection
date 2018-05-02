package javajournal.graphs;

import java.util.Collection;
import java.util.Iterator;

public class NodeIterator<T> implements Iterator<Node<T>> {

    private Iterator<Edge<T>> iterator;

    public NodeIterator(Collection<Edge<T>> neighbors) {
        iterator = neighbors.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Node<T> next() {
        return iterator.next().dest();
    }
}
