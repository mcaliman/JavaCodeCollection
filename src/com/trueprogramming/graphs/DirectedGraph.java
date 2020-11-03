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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DirectedGraph<T> implements Graph<T> {

    private HashMap<T, Node<T>> graph;

    public DirectedGraph() {
        this.graph = new HashMap<>();
    }

    @Override
    public boolean contains(T x) {
        return this.graph.containsKey(x);
    }

    @Override
    public boolean areAdjacent(T x, T y) throws NoSuchNodeException {
        if (noSuchNodes(x, y))
            throw new NoSuchNodeException();
        return areAdjacent(node(x), node(y));
    }

    private boolean areAdjacent(Node<T> u, Node<T> v) {
        return u.hasNeighbor(v);
    }

    @Override
    public void addNode(T x) {
        Node<T> u = new Node<>(x);
        this.graph.put(x, u);
    }

    @Override
    public void removeNode(T x) throws NoSuchNodeException {
        Node<T> u = node(x);
        if (noSuchNode(x))
            throw new NoSuchNodeException();
        Iterator<Node<T>> iterator = graph.values().iterator();
        while (iterator.hasNext()) {
            Node<T> w = iterator.next();
            w.removeEdgeTo(u);
        }
        graph.remove(x);
    }

    @Override
    public void addEdge(T x, T y) throws NoSuchNodeException {
        if (noSuchNodes(x, y))
            throw new NoSuchNodeException();
        addEdge(node(x), node(y));
    }

    private void addEdge(Node<T> u, Node<T> v) throws NoSuchNodeException {
        Edge<T> edge = new Edge<>(u, v);
        u.addEdge(edge);
    }

    @Override
    public void removeEdge(T x, T y) throws NoSuchNodeException {
        if (noSuchNodes(x, y))
            throw new NoSuchNodeException();
        Node<T> u = node(x);
        Node<T> v = node(y);
        if (u.hasNeighbor(v))
            u.removeEdgeTo(v);
    }

    @Override
    public List<T> getNeighbors(T x) throws NoSuchNodeException {        
        if (noSuchNode(x))
            throw new NoSuchNodeException();
        Node u = node(x);
        return u.getNeighbors();
    }

    @Override
    public List<T> depthSearch(T x) throws NoSuchNodeException {
        if (noSuchNode(x))
            throw new NoSuchNodeException();
        List<T> results = new ArrayList<>();
        Collection<T> visited = new HashSet<>();
        visited.add(x);

        Stack<T> stack = new Stack<>();
        stack.push(x);
        results.add(x);

        while (!stack.isEmpty()) {
            T current = stack.peek();
            T neighbor = null;
            Iterator<T> iterator = getNeighbors(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (notVisited(neighbor, visited))
                    break;
            }
            if (notVisited(neighbor, visited)) {
                visited.add(neighbor);
                results.add(neighbor);
                stack.push(neighbor);
            } else
                stack.pop();
        }
        return results;
    }

    @Override
    public List<T> breathSearch(T x) throws NoSuchNodeException {
        if (noSuchNode(x))
            throw new NoSuchNodeException();
        List<T> results = new ArrayList<>();
        Collection<T> visited = new HashSet<>();
        visited.add(x);

        Queue<T> queue = new ArrayDeque<>();
        queue.add(x);
        results.add(x);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            T neighbor = null;
            Iterator<T> iterator = getNeighbors(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (notVisited(neighbor, visited)) {
                    visited.add(neighbor);
                    results.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return results;
    }

    private boolean notVisited(T u, Collection<T> visited) {
        return u != null && !visited.contains(u);
    }

    @Override
    public List<T> topologicalSort() throws NoSuchNodeException {
        //Use kahn Top Sort
        List<T> result = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<Node>();
        Collection<Node<T>> nodes = nodes();
        for (Node<T> v : nodes)
            if (hasNoIncoimingEdges(v))
                queue.add(v);
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            result.add((T) v.value());
            List<Edge<T>> outgoingEdges = outgoingEdges(v);
            for (Edge e : outgoingEdges) {
                Node<T> s = e.src();
                Node<T> t = e.dest();
                this.removeEdge(s.value(), t.value());
                Node end = e.dest();
                if (hasNoIncoimingEdges(end))
                    queue.add(end);
            }
        }
        //if (hasEdges(graph)) {
        //    //return error;
        //}

        return result;
    }

    private boolean hasNoIncoimingEdges(Node<T> v) {
        return incomingEdges(v).isEmpty();
    }

    private Collection<Node<T>> nodes() {
        return this.graph.values();
    }

    @Override
    public List<Edge<T>> edges() {
        List<Edge<T>> results = new ArrayList<>();
        Collection<Node<T>> nodes = nodes();
        nodes.stream().map((node) -> node.edges()).forEachOrdered((egdes) -> {
            results.addAll(egdes);
        });
        return results;
    }

    @Override
    public List<Edge<T>> incomingEdges(Node v) {
        List<Edge<T>> incomingEdges = new ArrayList<>();
        List<Edge<T>> edges = this.edges();
        for (Edge<T> edge : edges)
            if (edge.dest().equals(v))
                incomingEdges.add(edge);
        return incomingEdges;
    }

    @Override
    public List<Edge<T>> outgoingEdges(Node v) {
        List<Edge<T>> outgoingEdges = new ArrayList<>();
        List<Edge<T>> edges = edges();
        for (Edge<T> edge : edges)
            if (edge.src().equals(v))
                outgoingEdges.add(edge);
        return outgoingEdges;
    }

    private Node<T> node(T item) {
        return graph.get(item);
    }

    private boolean noSuchNodes(T x, T y) {
        return noSuchNode(x) || noSuchNode(y);
    }

    private boolean noSuchNode(T item) {
        return node(item) == null;
    }

}
