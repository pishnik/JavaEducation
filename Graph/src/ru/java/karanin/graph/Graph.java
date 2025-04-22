package ru.java.karanin.graph;

import java.util.ArrayDeque;
import java.util.function.Consumer;

public class Graph<T> {
    // вершины
    private final T[] nodes;
    // ребра
    private final int[][] paths;
    // размер
    private int size;

    @SuppressWarnings("unchecked")
    public Graph(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(String.format("Вместимость графа должна быть больше 0, передано значение: %d", capacity));
        }

        // массив вершин
        nodes = (T[]) new Object[capacity];
        // массив ребер
        paths = new int[capacity][capacity];
    }

    public Graph() {
        this(10);
    }

    public int size() {
        return size;
    }

    public void addNode(T value) {
        if (size >= nodes.length) {
            throw new IllegalStateException(String.format("Превышение вместимости графа %d", size));
        }

        int nodeIndex = size;

        nodes[nodeIndex] = value;
        size++;
    }

    public void addPath(int node1Index, int node2Index) {
        if (node1Index < 0 || node1Index >= size) {
            throw new IndexOutOfBoundsException(String.format("Индекс первой вершины %d не входит в диапазон [0, %d])", node1Index, size - 1));
        }

        if (node2Index < 0 || node2Index >= size) {
            throw new IndexOutOfBoundsException(String.format("Индекс второй вершины %d не входит в диапазон [0, %d])", node2Index, size - 1));
        }

        paths[node1Index][node2Index] = 1;
        paths[node2Index][node1Index] = 1;
    }

    public void traversalBreadthFirst(Consumer<T> action) {
        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        // очередь
        ArrayDeque<Integer> queue = new ArrayDeque<>(size);

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                queue.add(i);

                while (!queue.isEmpty()) {
                    Integer currentNodeIndex = queue.pollFirst();

                    action.accept(nodes[currentNodeIndex]);

                    visited[currentNodeIndex] = true;

                    for (int j = 0; j < size; j++) {
                        if (paths[currentNodeIndex][j] > 0 && !visited[j] && !queue.contains(j)) {
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }

    public void traversalDepthFirst(Consumer<T> action) {
        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        // нам нужен стек
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                stack.push(i);

                while (!stack.isEmpty()) {
                    Integer currentNodeIndex = stack.pop();
                    action.accept(nodes[currentNodeIndex]);
                    visited[currentNodeIndex] = true;

                    for (int j = size - 1; j >= 0; j--) {
                        if (paths[currentNodeIndex][j] > 0 && !visited[j] && !stack.contains(j)) {
                            stack.push(j);
                        }
                    }
                }
            }
        }
    }

    public void traversalDepthFirstRecursive(Consumer<T> action) {
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            traverseDepthFirstRecursive(i, visited, action);
        }
    }

    private void traverseDepthFirstRecursive(Integer nodeIndex, boolean[] visited, Consumer<T> action) {
        if (visited[nodeIndex]) {
            return;
        }

        action.accept(nodes[nodeIndex]);
        visited[nodeIndex] = true;

        for (int j = 0; j < size; j++) {
            if (paths[nodeIndex][j] > 0 && !visited[j]) {
                traverseDepthFirstRecursive(j, visited, action);
            }
        }
    }
}