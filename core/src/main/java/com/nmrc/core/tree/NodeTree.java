/**
 * @author 16george
 */

package com.nmrc.core.tree;

public class NodeTree<E> {

    private E element;
    private NodeTree<E> left;
    private NodeTree<E> right;

    public NodeTree(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public NodeTree<E> getLeft() {
        return left;
    }

    public void setLeft(NodeTree<E> left) {
        this.left = left;
    }

    public NodeTree<E> getRight() {
        return right;
    }

    public void setRight(NodeTree<E> right) {
        this.right = right;
    }
}
