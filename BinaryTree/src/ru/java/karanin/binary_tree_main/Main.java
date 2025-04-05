package ru.java.karanin.binary_tree_main;

import ru.java.karanin.binary_tree.BinaryTree;
import ru.java.karanin.binary_tree.TreeNode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.addNode(8);
        tree.addNode(3);
        tree.addNode(10);
        tree.addNode(1);
        tree.addNode(6);
        tree.addNode(14);
        tree.addNode(4);
        tree.addNode(7);
        tree.addNode(13);
        tree.addNode(20);
        tree.addNode(17);
        tree.addNode(25);
        tree.addNode(18);

        System.out.printf("Размер дерева %d%n", tree.getSize());

        System.out.print("Введите число для поиска в дереве: ");
        Integer searchingValue = scanner.nextInt();

        TreeNode<Integer> node = tree.findNodeByValue(searchingValue);

        if (node != null) {
            System.out.printf("Значения %d в дереве есть%n", searchingValue);
        } else {
            System.out.printf("Значения %d в дереве нет%n", searchingValue);
        }

        System.out.print("Введите число для удаления из дерева: ");
        Integer deletingValue = scanner.nextInt();

        if (tree.deleteNodeByValue(deletingValue)) {
            System.out.printf("Число %d удалено из дерева%n", deletingValue);
        } else {
            System.out.printf("Число %d не было удалено из дерева%n", deletingValue);
        }

        tree.printByBreadthFirstTraversal();
        tree.printByDepthTraversalRecursive();
        tree.printByDepthTraversal();
    }
}