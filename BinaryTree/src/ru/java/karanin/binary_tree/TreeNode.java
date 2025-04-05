package ru.java.karanin.binary_tree;


public class TreeNode<E extends Comparable<E>> {
    private TreeNode<E> leftNode;

    private TreeNode<E> rightNode;

    private E value;

    public TreeNode(E value) {
        this.value = value;
    }

    public E getNodeValue() {
        return value;
    }

    public void setNodeValue(E value) {
        this.value = value;
    }

    public TreeNode<E> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode<E> leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode<E> getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode<E> rightNode) {
        this.rightNode = rightNode;
    }
}
