package javajournal.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Queue;
import java.util.Stack;

public class OrientedGraph<T> implements Graph<T> {

    private HashMap<T, Node<T>> graph;

    public OrientedGraph() {
        graph = new HashMap<>();
    }

    @Override
    public boolean contains(T item) {
        return graph.containsKey(item);
    }

    @Override
    public boolean areAdjacent(T source, T target) throws NoSuchNodeException {
        Node<T> sourceNode = getNode(source);
        Node<T> targetNode = getNode(target);
        if (isNull(sourceNode) || isNull(targetNode)) {
            throw new NoSuchNodeException();
        }
        return sourceNode.hasNeighbor(targetNode);
    }

    @Override
    public void addNode(T item) {
        Node<T> node = new Node<>(item);
        graph.put(item, node);
    }

    @Override
    public void removeNode(T item) throws NoSuchNodeException {
        Node<T> node = getNode(item);
        if (noSuchNode(item)) {
            throw new NoSuchNodeException();
        }
        Iterator<Node<T>> iterator = graph.values().iterator();
        while (iterator.hasNext()) {
            Node<T> possibleLink = iterator.next();
            possibleLink.removeEdgeTo(node);
        }
        graph.remove(item);
    }

    @Override
    public void addEdge(T source, T target, int weight) throws NoSuchNodeException {
        Node<T> sourceNode = getNode(source);
        Node<T> targetNode = getNode(target);
        if (isNull(sourceNode) || targetNode == null) {
            throw new NoSuchNodeException();
        }
        Edge<T> edge = new Edge<>(sourceNode, targetNode, weight);
        sourceNode.addEdge(edge);
    }

    @Override
    public void removeEdge(T source, T target) throws NoSuchNodeException {
        Node<T> sourceNode = getNode(source);
        Node<T> targetNode = getNode(target);
        if (isNull(sourceNode) || isNull(targetNode)) {
            throw new NoSuchNodeException();
        }
        if (sourceNode.hasNeighbor(targetNode)) {
            sourceNode.removeEdgeTo(targetNode);
        }
    }

    @Override
    public List<T> getNeighbors(T item) throws NoSuchNodeException {
        Node node = getNode(item);
        if (isNull(node)) {
            throw new NoSuchNodeException();
        }
        return node.getNeighbors();
    }

    @Override
    public List<T> depthSearch(T start) throws NoSuchNodeException {
        if (noSuchNode(start)) {
            throw new NoSuchNodeException();
        }
        List<T> elements = new ArrayList<>();
        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Stack<T> stack = new Stack<>();
        stack.push(start);
        elements.add(start);

        while (!stack.isEmpty()) {
            T current = stack.peek();
            T neighbor = null;
            Iterator<T> iterator = getNeighbors(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (notVisited(neighbor, visited)) {
                    break;
                }
            }
            if (notVisited(neighbor, visited)) {
                visited.add(neighbor);
                elements.add(neighbor);
                stack.push(neighbor);
            } else {
                stack.pop();
            }
        }
        return elements;
    }

    @Override
    public List<T> breathSearch(T start) throws NoSuchNodeException {
        if (noSuchNode(start)) {
            throw new NoSuchNodeException();
        }
        List<T> elements = new ArrayList<>();
        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Queue<T> queue = new ArrayDeque<>();
        queue.add(start);
        elements.add(start);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            T neighbor = null;
            Iterator<T> iterator = getNeighbors(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (notVisited(neighbor, visited)) {
                    visited.add(neighbor);
                    elements.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return elements;
    }

    private boolean notVisited(T node, Collection<T> visited) {
        return nonNull(node) && !visited.contains(node);
    }

    //distruttiva, usare copia di graph
    @Override
    public List<T> kahnTopSort() throws NoSuchNodeException {
        List<T> result = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<Node>() ;
        Collection<Node<T>> vertices = this.graph.values();
        for (Node<T> v : vertices) {
            
            if (hasNoIncoimingEdges(v)) {
                queue.add(v);
            }
        }
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            //result.push(v);
            result.add((T)v.value());
            System.out.println("push: "+v.value());
            List<Edge<T>> outgoingEdges = outgoingEdges(v);
            for (Edge e : outgoingEdges) {
                Node<T> s = e.getSource();
                Node<T> t = e.getTarget();
                this.removeEdge(s.value(), t.value());
                Node endV = e.getTarget();
                if (hasNoIncoimingEdges(endV)) {
                    //System.out.println("pushI: "+endV.value());
                    queue.add(endV);
                }
            }
        }
        if (hasEdges(graph)) {
            //return error;
        }

        return result;
    }
    
  
    private boolean hasNoIncoimingEdges(Node<T> v) {
        return incomingEdges(v).size()==0;
    }

    private Collection<Node<T>> nodes() {
        return this.graph.values();
    }

    @Override
    public List<Edge<T>> edges() {
        List<Edge<T>> results = new ArrayList<>();
        Collection<Node<T>> nodes = nodes();
        for (Node<T> node : nodes) {
            List<Edge<T>> egdes = node.edges();
            results.addAll(egdes);
        }
        return results;
    }

    @Override
    public List<Edge<T>> incomingEdges(Node v) {
        List<Edge<T>> incomingEdges = new ArrayList<>();
        List<Edge<T>> edges = this.edges();
        for (Edge<T> edge : edges) {
            if (edge.getTarget().equals(v)) {
                incomingEdges.add(edge);
            }
        }
        return incomingEdges;
    }
    
    @Override
    public List<Edge<T>> outgoingEdges(Node v) {
        List<Edge<T>> outgoingEdges = new ArrayList<>();
        List<Edge<T>> edges = this.edges();
        for (Edge<T> edge : edges) {
            if (edge.getSource().equals(v)) {
                outgoingEdges.add(edge);
            }
        }
        return outgoingEdges;
    }

  

    private Node<T> getNode(T item) {
        return graph.get(item);
    }

    private boolean noSuchNode(T item) {
        return getNode(item) == null;
    }

    private boolean hasEdges(HashMap<T, Node<T>> graph) {
        return false;
    }

}
