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

public class Edge<T> {

    private Node<T> src;
    private Node<T> dest;

    public Edge(Node<T> src, Node<T> dest) {
        this.src = src;
        this.dest = dest;
    }

    public Node<T> src() {
        return this.src;
    }

    public Node<T> dest() {
        return this.dest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((src == null) ? 0 : src.hashCode());
        result = prime * result + ((dest == null) ? 0 : dest.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        Edge<T> edge = (Edge<T>) object;
        if (src == null) {
            if (edge.src != null) {
                return false;
            }
        } else if (!src.equals(edge.src)) {
            return false;
        }
        if (dest == null) {
            if (edge.dest != null) {
                return false;
            }
        } else if (!dest.equals(edge.dest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + src + " -> " + dest + ")";
    }

}
