package org.example;

public class AVLtree {

    private static class Node {
        Node LChild, RChild;
        int height; // height taken as number of edges
        int key;

        Node(int k) {
            key = k;
            LChild = null;
            RChild = null;
            height = 0;
        }
    }

    private Node root;

    public void insert(int key) {// This method should insert a value into the AVL tree. The tree should
                                 // rebalance itself automatically when an element is inserted.
        root = treeInsert(root, key);
        return;
    }

    public void delete(int key) {
        root = treeDelete(root, key);
        return;
    }

    public String serialize() {
        return treeSerialize(root, getHeight(root));
    }

    private Node treeInsert(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }

        if (node.key >= key) { // insert left
            node.LChild = treeInsert(node.LChild, key);
        } else {
            node.RChild = treeInsert(node.RChild, key);
        }

        updateHeight(node);
        node = reBalancify(node);

        return node;
    }

    private Node treeDelete(Node node, int key) {
        if (node == null) {
            return node;
        }

        if (node.key == key) { // found the key!!!!
            if (getHeight(node) == 0) // no children!!!!
                return null;
            else {// node has at least one child
                if (node.RChild != null)
                    return getandDelteMin(node.RChild);
                else if (node.LChild != null)
                    return getandDelteMin(node.LChild);

            }
        }

        if (node.key >= key) { // should be left
            node.LChild = treeDelete(node.LChild, key);

        } else {
            node.RChild = treeDelete(node.RChild, key);
        }

        updateHeight(node);
        node = reBalancify(node);

        return node;
    }

    private String treeSerialize(Node node, int treeHeight) {

        if (treeHeight <= 0)
            return nodeSerialize(node);

        String temp = "";

        temp += treeSerialize(node == null ? null : node.LChild, treeHeight - 1);
        temp += nodeSerialize(node);
        temp += (treeSerialize(node == null ? null : node.RChild, treeHeight - 1));

        return temp;
    }

    private String nodeSerialize(Node node) {
        return (node != null ? Integer.toString(node.key) : "null") + ",";
    }

    private Node reBalancify(Node node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = getHeight(node.RChild) - getHeight(node.LChild); // balance factor equals the height or r
                                                                             // child minus l
        // if either is null, their height is -1

        if (balanceFactor > 1) { // RChild is bigger
            if (getHeight(node.RChild.RChild) > getHeight(node.RChild.LChild)) { // right-right
                // single left rotation
                node = LeftRotation(node);

            } else {// assumed right left case
                node.RChild = RightRotation(node.RChild);
                updateHeight(node);
                node = LeftRotation(node);
            }

        } else if (balanceFactor < -1) { // Lchild is bigger

            if (getHeight(node.LChild.LChild) > getHeight(node.LChild.RChild)) { // left-left
                // single right rotation
                node = RightRotation(node);

            } else {// assumed left right case
                node.LChild = LeftRotation(node.LChild);
                updateHeight(node);
                node = RightRotation(node);
            }
        }

        updateHeight(node);
        return node;
    }

    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }

        node.height = Math.max(getHeight(node.LChild), getHeight(node.RChild)) + 1;
        return;
    }

    private int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    private Node LeftRotation(Node rootNode) {
        Node newRoot = rootNode.RChild;
        rootNode.RChild = null;
        Node newLeftChild_RightChild = newRoot.LChild;
        newRoot.LChild = null;
        Node newLeftChild = rootNode;

        newLeftChild.RChild = newLeftChild_RightChild;
        newRoot.LChild = newLeftChild;

        updateHeight(newRoot.LChild.RChild);
        updateHeight(newRoot.LChild);
        updateHeight(newRoot);

        return newRoot;
    }

    private Node RightRotation(Node rootNode) {
        Node newRoot = rootNode.LChild;
        rootNode.LChild = null;
        Node newRightChild_LeftChild = newRoot.RChild;
        newRoot.RChild = null;
        Node newRightChild = rootNode;

        newRightChild.LChild = newRightChild_LeftChild;
        newRoot.RChild = newRightChild;

        updateHeight(newRoot.RChild.LChild);
        updateHeight(newRoot.RChild);
        updateHeight(newRoot);

        return newRoot;
    }

    private Node getandDelteMin(Node node) {
        while (node.LChild != null) {
            node = node.LChild;
        }
        Node temp = node;
        node = null;
        return temp;
    }

}
