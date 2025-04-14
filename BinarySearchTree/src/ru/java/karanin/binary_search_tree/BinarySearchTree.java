package ru.java.karanin.binary_search_tree;

import java.util.ArrayDeque;
import java.util.Comparator;
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

        // если есть компаратор будем сравнивать им
        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        // компаратора не было пробуем через приведение
        if (value1 instanceof Comparable) {
            return ((Comparable<E>) value1).compareTo(value2);
        }

        throw new UnsupportedOperationException(String.format("Объекты класса %s нельзя сравнить, нужно задать компаратор", value1.getClass()));
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

        while (true) {
            int compareResult = compare(value, currentNode.getValue());

            if (compareResult == 0) {
                return true;
            }

            if (compareResult < 0) {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean remove(E value) {
        // зачем что-то удалять если там ничего нет
        if (size == 0) {
            // дерево не поменялось, там и меняться нечему
            return false;
        }

        TreeNode<E> previousNode = null;
        TreeNode<E> currentNode = root;

        // поиск узла дерева
        while (currentNode != null) {
            int compareResult = compare(value, currentNode.getValue());

            if (compareResult == 0) {
                break;
            }

            previousNode = currentNode;

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
            TreeNode<E> nextNode;

            if (currentNode.getLeft() != null) {
                nextNode = currentNode.getLeft();
            } else {
                nextNode = currentNode.getRight();
            }

            if (previousNode == null) {
                // если узел корень = предыдущий пустой
                root = nextNode;
            } else if (previousNode.getLeft() == currentNode) {
                // левый узел в null
                previousNode.setLeft(nextNode);
            } else {
                // правый узел в null
                previousNode.setRight(nextNode);
            }
        } else {
            // есть оба узла
            TreeNode<E> previousMinLeftNode = currentNode;
            TreeNode<E> minLeftNode = currentNode.getRight();

            while (minLeftNode.getLeft() != null) {
                previousMinLeftNode = minLeftNode;
                minLeftNode = minLeftNode.getLeft();
            }

            if (previousMinLeftNode.getLeft() == minLeftNode) {
                previousMinLeftNode.setLeft(minLeftNode.getRight());
            } else {
                previousMinLeftNode.setRight(minLeftNode.getRight());
            }

            minLeftNode.setLeft(currentNode.getLeft());
            minLeftNode.setRight(currentNode.getRight());

            if (previousNode == null) {
                root = minLeftNode;
            } else if (previousNode.getLeft() == currentNode) {
                previousNode.setLeft(minLeftNode);
            } else {
                previousNode.setRight(minLeftNode);
            }
        }

        // уменьшим размер
        size--;
        // вернем что были изменения
        return true;
    }

    public void breadthFirstTraversal(Consumer<E> action) {
        if (size == 0) {
            return;
        }

        // очередь
        ArrayDeque<TreeNode<E>> queue = new ArrayDeque<>(size);
        // начинаем с корня
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.pollFirst();
            action.accept(currentNode.getValue());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    private void depthFirstTraversalRecursive(TreeNode<E> node, Consumer<E> action) {
        if (node == null) {
            return;
        }

        action.accept(node.getValue());

        depthFirstTraversalRecursive(node.getLeft(), action);
        depthFirstTraversalRecursive(node.getRight(), action);
    }

    public void depthFirstTraversalRecursive(Consumer<E> action) {
        depthFirstTraversalRecursive(root, action);
    }

    public void depthFirstTraversal(Consumer<E> action) {
        if (size == 0) {
            return;
        }

        // нам нужен стек
        ArrayDeque<TreeNode<E>> stack = new ArrayDeque<>();
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
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{");

        breadthFirstTraversal(value -> stringBuilder.append(value).append(", "));

        if (stringBuilder.length() > 1) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder.append('}').toString();
    }
}