package com.sjjg.btree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedBinaryTree implements IBinaryTree {
    private Node root;

    public LinkedBinaryTree() {
    }

    public LinkedBinaryTree(Node root) {
        this.root = root;
    }

    /**
     * 是否空树
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 树结点数量
     *
     * @return
     */
    @Override
    public int size() {
        return this.size(root);
    }

    private int size(Node root) {
        if (root != null) {
            //获取左子树的size
            int nl = this.size(root.leftChild);
            //获取右子树的size
            int nr = this.size(root.rightChild);

            return nl + nr + 1;
        } else {
            return 0;
        }
    }

    /**
     * 获取二叉树的高度
     *
     * @return
     */
    @Override
    public int getHeight() {
        return this.getHeight(root);
    }

    private int getHeight(Node root) {
        //从左子树或右子树选出高度较高的，再加1即是二叉树的高度
        if (root != null) {
            int nl = this.getHeight(root.leftChild);
            int nr = this.getHeight(root.rightChild);

            return nl > nr ? nl + 1 : nr + 1;
        } else {
            return 0;
        }
    }

    /**
     * 查询指定值的结点
     *
     * @param value
     * @return
     */
    @Override
    public Node findKey(int value) {
        return findKey(value, root);
    }

    private Node findKey(Object value, Node root) {
        //递归结束条件1：结点为空，可能是整个树的根节点，也可能是递归调用中叶子节点中左孩子和右孩子
        if (root == null) {
            return null;
            //递归的结束条件2：找到了
        } else if (root != null && root.value == value) {
            return root;
        } else {//递归体
            Node node1 = this.findKey(value, root.leftChild);
            Node node2 = this.findKey(value, root.rightChild);
            if (node1 != null && node1.value == value) {
                return node1;
            } else if (node2 != null && node2.value == value) {
                return node2;
            } else {
                return null;
            }
        }
    }

    /**
     * 前序递归遍历
     */
    @Override
    public void preOrderTraverse() {
        this.preOrderTraverse(root);
    }

    private void preOrderTraverse(Node root) {
        if (root != null) {
            //先输出节点的值
            System.out.print(root.value + " ");
            //遍历左子树
            this.preOrderTraverse(root.leftChild);
            //遍历右子树
            this.preOrderTraverse(root.rightChild);
        }
    }

    /**
     * 中序遍历递归操作
     */
    @Override
    public void inOrderTraverse() {
        this.inOrderTraverse(root);
    }

    private void inOrderTraverse(Node root) {
        if (root != null) {
            //遍历左子树
            this.inOrderTraverse(root.leftChild);
            //输出节点的值
            System.out.print(root.value + " ");
            //遍历右子树
            this.inOrderTraverse(root.rightChild);
        }
    }

    /**
     * 后序遍历递归操作
     */
    @Override
    public void postOrderTraverse() {
        this.postOrderTraverse(root);
    }

    /**
     * 后序遍历递归操作
     *
     * @param root 树根结点
     */
    private void postOrderTraverse(Node root) {
        if (root != null) {
            //遍历左子树
            this.postOrderTraverse(root.leftChild);
            //遍历右子树
            this.postOrderTraverse(root.rightChild);
            //输出节点的值
            System.out.print(root.value + " ");
        }
    }

    /**
     * 中序遍历非递归操作
     * 1）对于任意节点current，若该节点不为空则将该节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     * 2）若左子树为空，栈顶节点出栈，访问节点后将该节点的右子树置为current
     * 3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    @Override
    public void inOrderByStack() {
        // 创建栈
        Deque<Node> stack = new LinkedList<Node>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.value + " ");
                current = current.rightChild;
            }
        }
    }

    /**
     * 前序遍历非递归操作
     * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     * 2）若左子树为空，栈顶节点出栈，将该节点的右子树置为current
     * 3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    @Override
    public void preOrderByStack() {
    }

    /**
     * 后序遍历非递归操作
     * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     * 2）若左子树为空，取栈顶节点的右子树，如果右子树为空或右子树刚访问过，则访问该节点，并将preNode置为该节点
     * 3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    @Override
    public void postOrderByStack() {

    }

    /**
     * 按照层次遍历二叉树
     */
    @Override
    public void levelOrderByStack() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (queue.size() != 0) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node temp = queue.poll();
                System.out.print(temp.value + " ");
                if (temp.leftChild != null) {
                    queue.add(temp.leftChild);
                }
                if (temp.rightChild != null) {
                    queue.add(temp.rightChild);
                }
            }
        }
    }
}
