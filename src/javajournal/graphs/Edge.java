package javajournal.graphs;

public class Edge<T> {

    private Node<T> from;
    private Node<T> to;
    private int weight;

    public Edge(Node<T> from, Node<T> to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Node<T> dest() {
        return to;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        result = prime * result + weight;
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
        Edge<T> other = (Edge<T>) obj;
        if (from == null) {
            if (other.from != null) {
                return false;
            }
        } else if (!from.equals(other.from)) {
            return false;
        }
        if (to == null) {
            if (other.to != null) {
                return false;
            }
        } else if (!to.equals(other.to)) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        return true;
    }
}
