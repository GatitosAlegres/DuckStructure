/**
 * @author 16george
 */

package com.nmrc.core.linkedlist;


import com.nmrc.core.Node;
import com.nmrc.core.Structure;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<E> extends Structure<E> {

    private Node<E> head;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addStart(E element) {
        Node<E> node = new Node<>(element);
        if (head == null)
            this.head = node;
        else {
            Node<E> temp = head;
            node.setNext(temp);
            this.head = node;
        }
        size++;
    }

    public void addEnd(E element) {
        if (head == null) {
            addStart(element);
            return;
        }
        Node<E> node = new Node<>(element);
        Node<E> temp = head;
        while (temp.getNext() != null)
            temp = temp.getNext();
        temp.setNext(node);
        size++;
    }

    public void add(int index, E element) {
        Node<E> node = new Node<>(element);
        Node<E> temp = head;

        if (index == 0) {
            addStart(element);
            return;
        }

        for (int i = 0; i < index - 1; i++)
            temp = temp.getNext();

        node.setNext(temp.getNext());
        temp.setNext(node);
        size++;
    }

    public void remove(E element) {
        Node<E> temp = head;
        Node<E> prev = null;

        while (temp != null) {
            if (temp.getElement().equals(element)) {
                if (prev != null) {
                    prev.setNext(temp.getNext());
                } else head = temp.getNext();
                size--;
                break;
            }
            prev = temp;
            temp = temp.getNext();
        }

    }

    public int getIndex(E element) {
        Node<E> temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.getElement().equals(element))
                return index;
            index++;
            temp = temp.getNext();
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    @Override
    protected E top() {
        return head.getElement();
    }

    @Override
    public int count(Predicate<E> predicate) {
        int total = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (predicate.test(temp.getElement()))
                total++;

            temp = temp.getNext();
        }
        return total;
    }

    @Override
    public void forEach(Consumer<E> consumer) {
        Node<E> temp = head;
        while (temp != null) {
            consumer.accept(temp.getElement());
            temp = temp.getNext();
        }
    }

    @Override
    public <T> Structure<T> map(Function<? super E, ? extends T> function) {
        LinkedList<T> data = new LinkedList<>();
        Node<E> temp = head;
        while (temp != null) {
            data.addStart(function.apply(temp.getElement()));
            temp = temp.getNext();
        }
        return data;
    }

    @Override
    public Structure<E> filter(Predicate<? super E> predicate) {
        LinkedList<E> data = new LinkedList<>();
        Node<E> temp = head;
        while (temp != null) {
            if (predicate.test(temp.getElement()))
                data.addStart(temp.getElement());
            temp = temp.getNext();
        }
        return data;
    }

    @Override
    public <T> T reduce(T identity, BiFunction<T, ? super E, T> accumulator) {
        T total = identity;
        Node<E> temp = head;
        while (temp != null) {
            total = accumulator.apply(total, temp.getElement());
            temp = temp.getNext();
        }
        return total;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.getNext();
            while (next != null) {
                if (comparator.compare(current.getElement(), next.getElement()) > 0) {
                    E temp = current.getElement();
                    current.setElement(next.getElement());
                    next.setElement(temp);
                }
                next = next.getNext();
            }
            current = current.getNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int indexOf(E instance) {
        int index = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (instance.equals(temp.getElement()))
                return index;
            index++;
            temp = temp.getNext();
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> temp = head;
        while (temp != null) {
            sb.append(temp.getElement().toString()).append("\n");
            temp = temp.getNext();
        }
        return sb.toString();
    }
}
