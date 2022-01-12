/**
 * @author 16george
 */

package com.nmrc.core;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface StructureEssentials<E> {

    int count(Predicate<E> predicate);

    void forEach(Consumer<E> consumer);

    void sort(Comparator<? super E> comparator);

    <T> Structure<T> map(Function<? super E, ? extends T> function);

    Structure<E> filter(Predicate<? super E> predicate);

    <T> T reduce(T identity, BiFunction<T, ? super E, T> accumulator);

}
