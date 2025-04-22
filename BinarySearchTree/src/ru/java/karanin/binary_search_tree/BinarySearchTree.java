package ru.java.karanin.binary_search_tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    // начальный элемент
    private TreeNode<E> root;
    // размер дерева (количество элементов)
    private int size;
    // компаратор для сравнения элементов дерева
    private Comparator<? super E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private int compare(E value1, E value2) {
        // если есть компаратор будем сравнивать им
        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        // работа с null значениями
        if (value1 == null && value2 == null) {
            return 0;
        }

        if (value1 == null) {
            return -1;
        }

        if (value2 == null) {
            return 1;
        }

        // компаратора не было пробуем через приведение
        if (value1 instanceof Comparable) {
            return ((Comparable<E>) value1).compareTo(value2);
        }

        throw new ClassCastException(String.format("Объекты класса %s нельзя сравнить, нужно задать компаратор", value1.getClass()));
    }

    public void add(E value) {
        if (size == 0) {
            root = new TreeNode<>(value);
            // увеличили размер списка
            size++;

            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int compareResult = compare(value, currentNode.getValue());

            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(value));
                    size++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(value));
                    size++;

                    return;
                }
            }
        }
    }

    public boolean contains(E value) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int compareResult = compare(value, currentNode.getValue());

            if (compareResult == 0) {
                return true;
            }

            if (compareResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    private void replaceNodes(TreeNode<E> parentNode, TreeNode<E> currentNode, TreeNode<E> childNode) {
        if (parentNode == null) {
            root = childNode;
        } else if (parentNode.getLeft() == currentNode) {
            parentNode.setLeft(childNode);
        } else {
            parentNode.setRight(childNode);
        }
    }

    public boolean remove(E value) {
        // зачем что-то удалять если там ничего нет
        if (size == 0) {
            // дерево не поменялось, там и меняться нечему
            return false;
        }

        TreeNode<E> parentNode = null;
        TreeNode<E> currentNode = root;

        // поиск узла дерева
        while (currentNode != null) {
            int compareResult = compare(value, currentNode.getValue());

            if (compareResult == 0) {
                break;
            }

            parentNode = currentNode;

            if (compareResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        // ничего не нашли
        if (currentNode == null) {
            // ничего не меняли
            return false;
        }

        // нет детей вообще или один из детей пустой
        if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            TreeNode<E> childNode = (currentNode.getLeft() != null) ? currentNode.getLeft() : currentNode.getRight();

            replaceNodes(parentNode, currentNode, childNode);
        } else {
            // есть оба узла
            TreeNode<E> minLeftParentNode = currentNode;
            TreeNode<E> minLeftNode = currentNode.getRight();

            while (minLeftNode.getLeft() != null) {
                minLeftParentNode = minLeftNode;
                minLeftNode = minLeftNode.getLeft();
            }

            if (minLeftParentNode.getLeft() == minLeftNode) {
                minLeftParentNode.setLeft(minLeftNode.getRight());
            } else {
                minLeftParentNode.setRight(minLeftNode.getRight());
            }

            minLeftNode.setLeft(currentNode.getLeft());
            minLeftNode.setRight(currentNode.getRight());

            replaceNodes(parentNode, currentNode, minLeftNode);
        }

        // уменьшим размер
        size--;
        // вернем что были изменения
        return true;
    }

    public void traversalBreadthFirst(Consumer<E> action) {
        if (size == 0) {
            return;
        }

        // очередь
        Queue<TreeNode<E>> queue = new ArrayDeque<>(size);
        // начинаем с корня
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.poll();
            action.accept(currentNode.getValue());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    private void traversalDepthFirstRecursive(TreeNode<E> node, Consumer<E> action) {
        if (node == null) {
            return;
        }

        action.accept(node.getValue());

        traversalDepthFirstRecursive(node.getLeft(), action);
        traversalDepthFirstRecursive(node.getRight(), action);
    }

    public void traversalDepthFirstRecursive(Consumer<E> action) {
        traversalDepthFirstRecursive(root, action);
    }

    public void traversalDepthFirst(Consumer<E> action) {
        if (size == 0) {
            return;
        }

        // нам нужен стек
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            action.accept(node.getValue());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        traversalBreadthFirst(value -> stringBuilder.append(value).append(", "));

        if (stringBuilder.length() > 1) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder.append('}').toString();
    }
}