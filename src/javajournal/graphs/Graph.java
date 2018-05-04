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
package javajournal.graphs;

import java.util.Collection;
import java.util.List;

public interface Graph<T> {

    public boolean contains(T item);

    public void addNode(T item);

    public boolean areAdjacent(T item1, T item2) throws NoSuchNodeException;

    public void removeNode(T item) throws NoSuchNodeException;

    public List<Edge<T>> edges();

    public List<Edge<T>> incomingEdges(Node node);

    public List<Edge<T>> outgoingEdges(Node node);

    public void addEdge(T source, T target) throws NoSuchNodeException;

    public void removeEdge(T source, T target) throws NoSuchNodeException;

    public Collection<T> getNeighbors(T item) throws NoSuchNodeException;

    public List<T> depthSearch(T start) throws NoSuchNodeException;

    public List<T> breathSearch(T start) throws NoSuchNodeException;

    public List<T> topologicalSort() throws NoSuchNodeException;
   
}
