package ru.java.karanin.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph<E> {
    // вершины
    private final E[] vertices;
    // ребра
    private final int[][] edges;

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

        for (int i = 0; i < edges.length; i++) {
            if (edges.length != edges[i].length) {
                throw new IllegalArgumentException(String.format("Матрица ребер графа должна быть квадратной, найден массив длинны %d (необходим %d) в позиции %d", edges[i].length, edges.length, i));
            }
        }

        for (int i = 0; i < edges.length; i++) {
            if (edges[i][i] != 0) {
                throw new IllegalArgumentException(String.format("Не должно быть ребер из вершины в себя, найдено значение %d на позиции {%d, %d}", edges[i][i], i, i));
            }
        }

        // массив вершин
        this.vertices = Arrays.copyOf(vertices, vertices.length);
        // массив ребер
        this.edges = edges.clone();
    }

    public int size() {
        return vertices.length;
    }

    public void traverseBreadthFirst(Consumer<E> action) {
        if (vertices.length == 0) {
            return;
        }

        boolean[] visited = new boolean[vertices.length];

        // очередь
        Queue<Integer> queue = new ArrayDeque<>(vertices.length);

        for (int i = 0; i < vertices.length; i++) {
            if (visited[i]) {
                continue;
            }

            queue.add(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                int currentVertexIndex = queue.poll();
                action.accept(vertices[currentVertexIndex]);

                for (int j = 0; j < vertices.length; j++) {
                    if (edges[currentVertexIndex][j] != 0 && !visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
    }

    public void traverseDepthFirst(Consumer<E> action) {
        if (vertices.length == 0) {
            return;
        }

        boolean[] visited = new boolean[vertices.length];

        // нам нужен стек
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < vertices.length; i++) {
            if (visited[i]) {
                continue;
            }

            stack.push(i);
            visited[i] = true;

            while (!stack.isEmpty()) {
                int currentVertexIndex = stack.pop();
                action.accept(vertices[currentVertexIndex]);

                for (int j = vertices.length - 1; j >= 0; j--) {
                    if (edges[currentVertexIndex][j] != 0 && !visited[j]) {
                        stack.push(j);
                        visited[j] = true;
                    }
                }
            }
        }
    }

    public void traverseDepthFirstRecursive(Consumer<E> action) {
        boolean[] visited = new boolean[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            traverseDepthFirstRecursive(i, visited, action);
        }
    }

    private void traverseDepthFirstRecursive(Integer vertexIndex, boolean[] visited, Consumer<E> action) {
        if (visited[vertexIndex]) {
            return;
        }

        action.accept(vertices[vertexIndex]);
        visited[vertexIndex] = true;

        for (int i = 0; i < vertices.length; i++) {
            if (edges[vertexIndex][i] != 0 && !visited[i]) {
                traverseDepthFirstRecursive(i, visited, action);
            }
        }
    }
}