package javajournal.graphs;

public class Edge<T> {

    private Node<T> source;
    private Node<T> target;

    public Edge(Node<T> source, Node<T> target) {
        this.source = source;
        this.target = target;
    }

    public Node<T> getSource() {
        return source;
    }

    public Node<T> getTarget() {
        return target;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
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
        if (source == null) {
            if (edge.source != null) {
                return false;
            }
        } else if (!source.equals(edge.source)) {
            return false;
        }
        if (target == null) {
            if (edge.target != null) {
                return false;
            }
        } else if (!target.equals(edge.target)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + target + ")";
    }
    
    
}
