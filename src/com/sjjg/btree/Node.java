package com.sjjg.btree;

/**
 * 二叉链表的结点
 * @author 西邮陈冠希
 */
public class Node {
    Object value;
    Node leftChild;
    Node rightChild;

    public Node(Object value) {
        this.value = value;
    }

    public Node(Object value, Node leftChild, Node rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
