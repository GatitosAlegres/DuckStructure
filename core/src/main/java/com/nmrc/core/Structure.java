/**
 * @author 16george
 */

package com.nmrc.core;

public abstract class Structure<E> implements StructureEssentials<E> {

    protected int size;

    public abstract boolean isEmpty();

    protected abstract E top();
}
