package ru.java.karanin.binary_tree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree<E extends Comparable<E>> {
    // начальный элемент
    TreeNode<E> root;

    // размер дерева (количество элементов)
    int size;

    public BinaryTree() {
    }

    public int getSize() {
        return size;
    }

    public void addNode(E value) {
        if (size == 0) {
            root = new TreeNode<>(value);
            // увеличили размер списка
            size++;
            return;
        }

        TreeNode<E> currentNode = root;
        int compareResult;

        while (true) {
            compareResult = value.compareTo(currentNode.getNodeValue());

            if (compareResult < 0) {
                if (currentNode.getLeftNode() != null) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode.setLeftNode(new TreeNode<>(value));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRightNode() != null) {
                    currentNode = currentNode.getRightNode();
                } else {
                    currentNode.setRightNode(new TreeNode<>(value));
                    size++;
                    return;

                }
            }
        }
    }

    public TreeNode<E> findNodeByValue(E value) {
        if (size == 0) {
            return null;
        }

        TreeNode<E> currentNode = root;

        int compareResult;

        while (true) {
            compareResult = value.compareTo(currentNode.getNodeValue());

            if (compareResult == 0) {
                return currentNode;
            }

            if (compareResult < 0) {
                if (currentNode.getLeftNode() == null) {
                    return null;
                }

                currentNode = currentNode.getLeftNode();
            }

            if (compareResult > 0) {
                if (currentNode.getRightNode() == null) {
                    return null;
                }

                currentNode = currentNode.getRightNode();
            }
        }
    }

    public boolean deleteNodeByValue(E value) {
        // зачем что то удалять если там ничего нет
        if (size == 0) {
            // дерево не поменялось, там и меняться нечему
            return false;
        }

        TreeNode<E> previousNode = null;
        TreeNode<E> currentNode = root;

        int compareResult;

        // поиск узла дерева
        while (currentNode != null) {
            compareResult = value.compareTo(currentNode.getNodeValue());

            if (compareResult != 0) {
                previousNode = currentNode;

                if (compareResult < 0) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }
            } else {
                break;
            }
        }

        // ничего не нашли
        if (currentNode == null) {
            // ничего не меняли
            return false;
        }

        if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
            // у узла нет детей
            if (previousNode == null) {
                // если узел корень = предыдущий пустой
                root = null;
            } else {
                // надо понять какой узел затирать
                if (previousNode.getLeftNode() == currentNode) {
                    // левый узел в null
                    previousNode.setLeftNode(null);
                } else {
                    // правый узел в null
                    previousNode.setRightNode(null);
                }
            }
        } else if (currentNode.getRightNode() == null) {
            // есть левый узел
            if (previousNode == null) {
                root = currentNode.getLeftNode();
            } else if (previousNode.getLeftNode() == currentNode) {
                previousNode.setLeftNode(currentNode.getLeftNode());
            } else {
                previousNode.setRightNode(currentNode.getLeftNode());
            }
        } else if (currentNode.getLeftNode() == null) {
            // есть правый узел
            if (previousNode == null) {
                root = currentNode.getRightNode();
            } else if (previousNode.getLeftNode() == currentNode) {
                previousNode.setLeftNode(currentNode.getRightNode());
            } else {
                previousNode.setRightNode(currentNode.getRightNode());
            }
        } else {
            // есть оба узла
            TreeNode<E> previousMinLeftNode = currentNode;
            TreeNode<E> minLeftNode = currentNode.getRightNode();

            while (minLeftNode.getLeftNode() != null) {
                previousMinLeftNode = minLeftNode;
                minLeftNode = minLeftNode.getLeftNode();
            }

            // меняем значение удаляемому элементу на левый нижний узел, что бы не заменять ссылки
            currentNode.setNodeValue(minLeftNode.getNodeValue());

            if (previousMinLeftNode.getLeftNode() == minLeftNode) {
                previousMinLeftNode.setLeftNode(minLeftNode.getRightNode());
            } else {
                previousMinLeftNode.setRightNode(minLeftNode.getRightNode());
            }
        }

        // уменьшим размер
        size--;
        // вернем что были изменения
        return true;
    }

    public void printByBreadthFirstTraversal() {
        if (size == 0) {
            return;
        }

        // очередь
        ArrayList<TreeNode<E>> queue = new ArrayList<>(size);
        // начинаем с корня
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove(0);

            System.out.println(currentNode.getNodeValue());

            if (currentNode.getLeftNode() != null) {
                queue.add(currentNode.getLeftNode());
            }

            if (currentNode.getRightNode() != null) {
                queue.add(currentNode.getRightNode());
            }
        }
    }

    private void depthFirstTraversalOne(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getNodeValue());

        depthFirstTraversalOne(node.getLeftNode());
        depthFirstTraversalOne(node.getRightNode());
    }

    public void printByDepthTraversalRecursive() {
        depthFirstTraversalOne(root);
    }

    public void printByDepthTraversal() {
        if (size == 0) {
            return;
        }

        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);

        TreeNode<E> node;

        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node.getNodeValue());

            if (node.getRightNode() != null) {
                stack.push(node.getRightNode());
            }

            if (node.getLeftNode() != null) {
                stack.push(node.getLeftNode());
            }
        }
    }
}