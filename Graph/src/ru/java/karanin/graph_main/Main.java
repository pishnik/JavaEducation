package ru.java.karanin.graph_main;

import ru.java.karanin.graph.Graph;

public class Main {
    public static void main(String[] args) {

        String[] towns = new String[]{
                "Кемерово",
                "Белово",
                "Новокузнецк",
                "Новосибирск",
                "Томск",
                "Киселевск",
                "Ленинск-Кузнецкий",
                "Юрга"};

        int[][] roads = new int[towns.length][towns.length];

        roads[0][3] = 1;
        roads[0][4] = 1;
        roads[0][6] = 1;
        roads[1][2] = 1;
        roads[1][5] = 1;
        roads[6][1] = 1;
        roads[3][4] = 1;
        roads[0][7] = 1;
        roads[7][3] = 1;

        Graph<String> graph = new Graph<>(towns, roads);

        System.out.println("Обход в ширину:");
        graph.traversalBreadthFirst(System.out::println);
        System.out.println();

        System.out.println("Обход в глубину:");
        graph.traversalDepthFirst(System.out::println);
        System.out.println();

        System.out.println("Обход в глубину с рекурсией:");
        graph.traversalDepthFirstRecursive(System.out::println);
    }
}