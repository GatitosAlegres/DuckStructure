/**
 * @author 16george
 */

package com.nmrc.core.queue;

import com.nmrc.core.Node;
import com.nmrc.core.Structure;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Queue<E> extends Structure<E> {

    private Node<E> first;
    private Node<E> last;

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(E element) {
        Node<E> instance = new Node<>(element);

        if (first == null) {
            first = instance;
        } else {
            last.setNext(instance);
        }
        last = instance;
        size++;
    }

    public void dequeue() {
        if (!isEmpty()) {
            Node<E> temp = first;
            first = first.getNext();
            temp.setNext(null);
            if (first == null)
                last = null;
            size--;
        }
    }

    public int length() {
        return size;
    }

    @Override
    public E top() {
        return first.getElement();
    }

    @Override
    public int count(Predicate<E> predicate) {
        int total = 0;
        Node<E> temp = first;
        while (temp != null) {
            if (predicate.test(temp.getElement()))
                total++;

            temp = temp.getNext();
        }
        return total;
    }

    @Override
    public void forEach(Consumer<E> consumer) {
        Node<E> temp = first;
        while (temp != null) {
            consumer.accept(temp.getElement());
            temp = temp.getNext();
        }
    }

    @Override
    public <T>Queue<T> map(Function<? super E, ? extends T> function) {
        Queue<T> data = new Queue<>();
        Node<E> temp = first;
        while (temp != null) {
            data.enqueue(function.apply(temp.getElement()));
            temp = temp.getNext();
        }
        return data;
    }

    @Override
    public Queue<E> filter(Predicate<? super E> predicate) {
        Queue<E> data = new Queue<>();
        Node<E> temp = first;
        while (temp != null) {
            if (predicate.test(temp.getElement()))
                data.enqueue(temp.getElement());
            temp = temp.getNext();
        }
        return data;
    }

    @Override
    public <T> T reduce(T identity, BiFunction<T, ? super E, T> accumulator) {
        T total = identity;
        Node<E> temp = first;
        while (temp != null) {
            total = accumulator.apply(total, temp.getElement());
            temp = temp.getNext();
        }
        return total;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Node<E> current = first;
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
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = first;
        while(current != null) {
            sb.append(current.getElement()).append("\n");
            current = current.getNext();
        }
        return sb.toString();
    }
}
