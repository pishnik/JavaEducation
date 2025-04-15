package ru.java.karanin.graph_main;

import ru.java.karanin.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addNode("Кемерово");
        graph.addNode("Белово");
        graph.addNode("Новокузнецк");
        graph.addNode("Новосибирск");
        graph.addNode("Томск");
        graph.addNode("Киселевск");
        graph.addNode("Ленинск-Кузнецкий");
        graph.addNode("Юрга");

        graph.addPath(0, 3);
        graph.addPath(0, 4);
        graph.addPath(0, 6);
        graph.addPath(1, 2);
        graph.addPath(1, 5);
        graph.addPath(6, 1);
        graph.addPath(3, 4);
        graph.addPath(0, 7);
        graph.addPath(7, 3);

        System.out.println("Обход в ширину:");
        graph.breadthFirstTraversal(System.out::println);
        System.out.println();

        System.out.println("Обход в глубину:");
        graph.depthFirstTraversal(System.out::println);
        System.out.println();

        System.out.println("Обход в глубину с рекурсией:");
        graph.depthFirstTraversalRecursive(System.out::println);
    }
}