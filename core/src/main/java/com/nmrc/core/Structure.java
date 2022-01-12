/**
 * @author 16george
 */

package com.nmrc.core;

public abstract class Structure<E> implements StructureEssentials<E> {

    protected int size;

    protected abstract boolean isEmpty();

    protected abstract E top();
}
