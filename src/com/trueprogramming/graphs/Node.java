/*
 The MIT License (MIT)

 Copyright (c) 2017 Massimo Caliman

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
package com.trueprogramming.graphs;

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

    private Edge<T> getEdgeTo(Node<T> dest) {
        Iterator<Edge<T>> edges = neighbors.iterator();
        while (edges.hasNext()) {
            Edge<T> current = edges.next();
            if (current.dest().equals(dest))
                return current;
        }
        return null;
    }

    public List<Edge<T>> edges() {
        return this.neighbors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (data == null ? 0 : data.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node<T> other = (Node<T>) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new NodeIterator<>(neighbors);
    }

    public void addEdge(Edge<T> edge) {
        if (!neighbors.contains(edge))
            neighbors.add(edge);
    }

    public boolean hasNeighbor(Node<T> neighbor) {
        Iterator<Node<T>> it = iterator();
        while (it.hasNext()) {
            Node node = it.next();
            if (node.equals(neighbor))
                return true;
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
        while (it.hasNext())
            neighborsList.add(it.next().value());
        return neighborsList;
    }

    public T value() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
