package ru.java.karanin.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph<E> {
    // вершины
    private final E[] vertices;
    // ребра
    private final int[][] edges;
    // размер
    private final int size;

    public Graph(E[] vertices, int[][] edges) {
        if (vertices.length == 0) {
            throw new IllegalArgumentException("Массив вершин графа пустой");
        }

        if (edges.length == 0) {
            throw new IllegalArgumentException("Массив ребер графа пустой");
        }

        if (edges.length != vertices.length) {
            throw new IllegalArgumentException(String.format("Размерность матрицы ребер графа (edges.length = %d) не соответствует количеству вершин графа (vertices.length = %d)", edges.length, vertices.length));
        }

        // массив вершин
        this.vertices = vertices;
        // массив ребер
        this.edges = edges;
        // размер
        size = vertices.length;
    }

    public int size() {
        return size;
    }

    public void traverseBreadthFirst(Consumer<E> action) {
        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        // очередь
        Queue<Integer> queue = new ArrayDeque<>(size);

        for (int i = 0; i < size; i++) {
            if (visited[i]) {
                continue;
            }

            queue.add(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                Integer currentVertexIndex = queue.poll();
                action.accept(vertices[currentVertexIndex]);

                for (int j = 0; j < size; j++) {
                    if (edges[currentVertexIndex][j] != 0 && !visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }

        }
    }

    public void traverseDepthFirst(Consumer<E> action) {
        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        // нам нужен стек
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            if (visited[i]) {
                continue;
            }

            stack.push(i);
            visited[i] = true;

            while (!stack.isEmpty()) {
                Integer currentVertexIndex = stack.pop();
                action.accept(vertices[currentVertexIndex]);

                for (int j = size - 1; j >= 0; j--) {
                    if (edges[currentVertexIndex][j] != 0 && !visited[j]) {
                        stack.push(j);
                        visited[j] = true;
                    }
                }
            }
        }
    }

    public void traverseDepthFirstRecursive(Consumer<E> action) {
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            traverseDepthFirstRecursive(i, visited, action);
        }
    }

    private void traverseDepthFirstRecursive(Integer vertexIndex, boolean[] visited, Consumer<E> action) {
        if (visited[vertexIndex]) {
            return;
        }

        action.accept(vertices[vertexIndex]);
        visited[vertexIndex] = true;

        for (int i = 0; i < size; i++) {
            if (edges[vertexIndex][i] != 0 && !visited[i]) {
                traverseDepthFirstRecursive(i, visited, action);
            }
        }
    }
}