/**
 * @author 16george
 */

package com.nmrc.core.tree;

import com.nmrc.core.model.Medicine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tree<E extends Medicine> {

    private NodeTree<E> root;
    private Comparator<E> comparator;
    private List<E> enumerated;

    public Tree(Comparator<E> comparator) {
        root = null;
        this.comparator = comparator;
        this.enumerated = new ArrayList<>();
    }

    public void setRoot(NodeTree<E> root) {
        this.root = root;
    }

    public List<E> getEnumerated() {
        return enumerated;
    }

    public void setEnumerated(List<E> enumerated) {
        this.enumerated = enumerated;
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
            enumerated.add(root.getElement());
            inOrder(root.getRight());
        }
    }

    public NodeTree<E> getRoot() {
        return root;
    }

    public void preOrder(NodeTree<E> root) {
        if (root != null) {
            enumerated.add(root.getElement());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void postOrder(NodeTree<E> root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            enumerated.add(root.getElement());
        }
    }

    public NodeTree<E> binarySearch(Medicine element) {
        NodeTree<E> aux = root;
        while (aux != null) {
            if (element.getName().equals(aux.getElement().getName())) return aux;
            else if (comparator.compare((E) element, aux.getElement()) < 0) aux = aux.getLeft();
            else aux = aux.getRight();
        }
        return null;
    }

    public void modify(E element) {
        NodeTree<E> aux = binarySearch(element);
        if (aux != null) aux.setElement(element);
    }

    public NodeTree<E> delete(NodeTree<E> root, E key)
    {

        if (root == null)
            return root;

        if (comparator.compare(key, root.getElement()) < 0)
            root.setLeft(delete(root.getLeft(), key));
        else if (comparator.compare(key, root.getElement()) > 0)
            root.setRight(delete(root.getRight(), key));
        else {
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            root.setElement(minValue(root.getRight()));

            root.setRight(delete(root.getRight(), root.getElement()));
        }

        return root;
    }

    public E minValue(NodeTree<E> root)
    {
        E minv = root.getElement();
        while (root.getLeft() != null)
        {
            minv = root.getLeft().getElement();
            root = root.getLeft();
        }
        return minv;
    }

    public Comparator<E> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }
}
