//package com.artemsirosh.tij.collections;
//
//import com.sun.istack.internal.NotNull;
//import typeinfo.pets.*;
//import java.util.*;
///**
// * Created by cresh on 15.03.16.
// */
//public class CollectionSequnce implements Collection<Pet> {
//    private Pet[] pets = Pets.createArray(8);
//
//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public int size() {
//        return pets.length;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public Iterator<Pet> iterator() {
//        return new Iterator<Pet>() {
//            private int index = 0;
//            @Override
//            public boolean hasNext() {
//                return pets.length>index;
//            }
//
//            @Override
//            public Pet next() {
//                return pets[index++];
//            }
//
//            @Override
//            public void remove() {
//                throw new UnsupportedOperationException();
//            }
//        };
//    }
//
//    @Override
//    public Object[] toArray() {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public <T> T[] toArray(T[] a) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean add(Pet pet) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends Pet> c) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        throw new UnsupportedOperationException();
//    }
//
//    public static void main(String[] args) {
//        CollectionSequnce c = new CollectionSequnce();
//        InterfaceVsIterator.display(c);
//        InterfaceVsIterator.display(c.iterator());
//    }
//}
