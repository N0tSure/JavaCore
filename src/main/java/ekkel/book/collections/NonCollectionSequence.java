//package ekkel.book.collections;
//
//import typeinfo.pets.*;
//import java.util.*;
///**
// * Created by cresh on 15.03.16.
// */
//public class NonCollectionSequence extends PetSequence implements Iterable<Pet> {
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
//    public Iterable<Pet> reversed() {
//        return new Iterable<Pet>() {
//            public Iterator<Pet> iterator() {
//                return new Iterator<Pet>() {
//                    int index = pets.length-1;
//                    @Override
//                    public boolean hasNext() {
//                        return index>-1;
//                    }
//
//                    @Override
//                    public Pet next() {
//                        return pets[index--];
//                    }
//
//                    @Override
//                    public void remove() {
//                        throw new UnsupportedOperationException();
//                    }
//                };
//            }
//        };
//    }
//    public Iterable<Pet> randomized() {
//        return new Iterable<Pet>() {
//            @Override
//            public Iterator<Pet> iterator() {
//                List<Pet> list = new ArrayList<Pet>(Arrays.asList(pets));
//                Collections.shuffle(list);
//                return list.iterator();
//            }
//        };
//    }
//
//    public static void main(String[] args) {
//        NonCollectionSequence sequence = new NonCollectionSequence();
//        InterfaceVsIterator.display(sequence.iterator());
//        for (Pet p : sequence) {
//            System.out.print(p+" ");
//        }
//        System.out.println("\nReversed:");
//
//        for (Pet p : sequence.reversed()) {
//            System.out.print(p+" ");
//        }
//
//        System.out.println("\nRandomized:");
//
//        for (Pet p : sequence.randomized()) {
//            System.out.print(p+" ");
//        }
//
//
//    }
//}
