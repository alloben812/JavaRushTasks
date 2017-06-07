package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by NazarenkoDS on 07.06.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable,Cloneable,Set<E> {
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
