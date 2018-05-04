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
                if (notVisited(neighbor,visited)) {
                    break;
                }
            }
            if (notVisited(neighbor,visited)) {
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
                if (notVisited(neighbor,visited)) {
                    visited.add(neighbor);
                    elements.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return elements;
    }

    private boolean notVisited(T node,Collection<T> visited){
        return nonNull(node) && !visited.contains(node);
    }
    
    @Override
    public List<T> topologicalSort() throws NoSuchNodeException {
        Iterator<T> iterator = this.graph.keySet().iterator();
        Collection<T> visited = new ArrayList<>();
        List<T> elements = new ArrayList<>();

        Stack<T> stack = new Stack<>();

        while (iterator.hasNext()) {
            T next = iterator.next();
            //visited.add(next);
            //ordered.add(next);
            if (!visited.contains(next)) {
                topologicalSortUtil(next, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            T el = stack.pop();
            elements.add(el);
            System.out.println("topsearch " + el + " ");
        }

        return elements;
    }

    void topologicalSortUtil(T next, Collection<T> visited, Stack stack) throws NoSuchNodeException {
        // Mark the current getNode as visited.

        visited.add(next);
        T i;

        // Recur for all the vertices adjacent to this
        // vertex
        List<T> list = this.getNeighbors(next);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited.contains(i)) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Push current vertex to stack which stores result
        stack.push(next);
    }

    
    private Node<T> getNode(T item){
         return graph.get(item);
    }
    private boolean noSuchNode(T item){
        return getNode(item) == null;
    }
}
