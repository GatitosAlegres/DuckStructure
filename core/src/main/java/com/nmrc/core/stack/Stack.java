/**
 * @author 16george
 */

package com.nmrc.core.stack;


import com.nmrc.core.Node;
import com.nmrc.core.Structure;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Stack<E> extends Structure<E> {

    private Node<E> top;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(E element) {
        Node<E> instance = new Node<E>(element);
        instance.setNext(top);
        top = instance;
        size++;
    }

    public void pop() {
        if (!isEmpty()) {
            Node<E> temp = top;
            top = top.getNext();
            temp.setNext(null);
            size--;
        }
    }

    public E peek() {
        if (isEmpty())
            return null;
        return top.getElement();
    }

    public int length() {
        return size;
    }

    @Override
    public E top() {
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int count(Predicate<E> predicate) {
        int total = 0;
        Node<E> temp = top;
        while (temp != null) {
            if (predicate.test(temp.getElement()))
                total++;

            temp = temp.getNext();
        }
        return total;
    }

    @Override
    public void forEach(Consumer<E> consumer) {
        Node<E> temp = top;
        while (temp != null) {
            consumer.accept(temp.getElement());
            temp = temp.getNext();
        }
    }

    @Override
    public <T> Stack<T> map(Function<? super E, ? extends T> function) {
        Stack<T> data = new Stack<>();
        Node<E> temp = top;
        while (temp != null) {
            data.push(function.apply(temp.getElement()));
            temp = temp.getNext();
        }
        return data;
    }

    @Override
    public Stack<E> filter(Predicate<? super E> predicate) {
        Stack<E> data = new Stack<>();
        Node<E> temp = top;
        while (temp != null) {
            if (predicate.test(temp.getElement()))
                data.push(temp.getElement());
            temp = temp.getNext();
        }
        return data;
    }

    @Override
    public <T> T reduce(T identity, BiFunction<T, ? super E, T> accumulator) {
        T total = identity;
        Node<E> temp = top;
        while (temp != null) {
            total = accumulator.apply(total, temp.getElement());
            temp = temp.getNext();
        }
        return total;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Node<E> current = top;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> temp = top;
        while (temp != null) {
            sb.append(temp.getElement()).append("\n");
            temp = temp.getNext();
        }
        return sb.toString();
    }
}
