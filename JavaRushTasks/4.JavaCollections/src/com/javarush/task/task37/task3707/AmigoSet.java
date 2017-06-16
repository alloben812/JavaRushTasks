package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

/**
 * Created by NazarenkoDS on 07.06.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable,Cloneable,Set<E> {
    private final static Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int) (collection.size() / .75f + 1)));
        this.addAll(collection);
    }

    public AmigoSet() {
        this.map = new HashMap<E, Object>();
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Spliterator<E> spliterator() {
        return map.keySet().spliterator();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet) super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();
        // Write out size
        s.writeInt(map.size());
        // Write out all elements in the proper order.
        for (E e : map.keySet())
            s.writeObject(e);
        // Write out HashMap capacity and load factor
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));




    }

    private void readObject(ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();
        int size = s.readInt();
        // Read capacity and verify non-negative.
        Set<E> set = new HashSet<>();
        for(int i =0;i<size;i++){
            set.add((E)s.readObject());
        }

        int capacity = s.readInt();


        // Read load factor and verify positive and non NaN.
        float loadFactor = s.readFloat();


        // Read size and verify non-negative.



        // Create backing HashMap
        map = new HashMap<E,Object>(capacity, loadFactor);

        // Read in all elements in the proper order.
        for(E e:set){
            map.put(e,PRESENT);
        }
    }
}