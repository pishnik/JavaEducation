package ru.java.karanin.binary_search_tree_main;

import ru.java.karanin.binary_search_tree.BinarySearchTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(null);
        tree.add(13);
        tree.add(20);
        tree.add(17);
        tree.add(25);
        tree.add(null);
        tree.add(18);

        System.out.printf("Размер дерева %d%n", tree.getSize());

        System.out.print("Введите число для поиска в дереве: ");
        Integer searchingValue = scanner.nextInt();

        if (tree.contains(searchingValue)) {
            System.out.printf("Значения %d в дереве есть%n", searchingValue);
        } else {
            System.out.printf("Значения %d в дереве нет%n", searchingValue);
        }

        System.out.print("Введите число для удаления из дерева: ");
        Integer deletingValue = scanner.nextInt();

        if (tree.remove(deletingValue)) {
            System.out.printf("Число %d удалено из дерева%n", deletingValue);
        } else {
            System.out.printf("Число %d не было удалено из дерева%n", deletingValue);
        }

        System.out.printf("Обход в ширину: %s%n", tree);

        System.out.println("Обход в ширину");
        tree.traversalBreadthFirst(System.out::println);

        System.out.println("Обход в глубину");
        tree.traversalDepthFirst(System.out::println);

        System.out.println("Обход в глубину с рекурсией");
        tree.traversalDepthFirstRecursive(System.out::println);
    }
}