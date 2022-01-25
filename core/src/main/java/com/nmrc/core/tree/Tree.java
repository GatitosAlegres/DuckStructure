/**
 * @author 16george
 */

package com.nmrc.core.tree;

import java.util.Comparator;

public class Tree<E> {

    private NodeTree<E> root;
    private Comparator<E> comparator;

    public Tree(Comparator<E> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public void add(E element) {
        NodeTree<E> nuevo = new NodeTree<E>(element);
        if (root == null) root = nuevo;

        else {
            NodeTree<E> aux = root;
            NodeTree<E> parent;
            while (true) {
                parent = aux;
                if (comparator.compare(element, aux.getElement()) < 0) {
                    aux = aux.getLeft();
                    if (aux == null) {
                        parent.setLeft(nuevo);
                        return;
                    }
                } else {
                    aux = aux.getRight();
                    if (aux == null) {
                        parent.setRight(nuevo);
                        return;
                    }
                }
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void inOrder(NodeTree<E> root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getElement());
            inOrder(root.getRight());
        }
    }

    public NodeTree<E> getRoot() {
        return root;
    }

    public void preOrder(NodeTree<E> root) {
        if (root != null) {
            System.out.println(root.getElement());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void postOrder(NodeTree<E> root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.println(root.getElement());
        }
    }

    public NodeTree<E> binarySearch(E element) {
        NodeTree<E> aux = root;
        while (aux != null) {
            if (comparator.compare(element, aux.getElement()) == 0) return aux;
            else if (comparator.compare(element, aux.getElement()) < 0) aux = aux.getLeft();
            else aux = aux.getRight();
        }
        return null;
    }

    public void modify(E element) {
        NodeTree<E> aux = binarySearch(element);
        if (aux != null) aux.setElement(element);
    }

    public void delete(E element) {
        NodeTree<E> aux = binarySearch(element);
        if (aux != null) {
            if (aux.getLeft() == null && aux.getRight() == null) root = null;
            else if (aux.getLeft() == null) root = aux.getRight();
            else if (aux.getRight() == null) root = aux.getLeft();
            else {
                NodeTree<E> aux2 = aux.getRight();
                while (aux2.getLeft() != null) aux2 = aux2.getLeft();
                aux.setElement(aux2.getElement());
                aux.setRight(aux2.getRight());
            }
        }
    }

    public Comparator<E> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }
}
