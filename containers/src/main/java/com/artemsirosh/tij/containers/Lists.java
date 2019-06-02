package com.artemsirosh.tij.containers;//: containers/Lists.java
// Things you can do with Lists.
import java.util.*;

import com.artemsirosh.tij.util.FlyweightMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Lists {
  private static boolean bool;
  private static String string;
  private static int i;
  private static Iterator<String> iterator;
  private static ListIterator<String> listIterator;
  private static final Logger LOGGER = LoggerFactory.getLogger(Lists.class);

  private static void basicTest(List<String> a) {
    a.add(1, "x"); // Add at location 1
    a.add("x"); // Add at end
    // Add a collection:
    a.addAll(FlyweightMap.names(25));
    // Add a collection starting at location 3:
    a.addAll(3, FlyweightMap.names(25));
    bool = a.contains("1"); // Is iterator in there?
    // Is the entire collection in there?
    bool = a.containsAll(FlyweightMap.names(25));
    // Lists allow random access, which is cheap
    // for ArrayList, expensive for LinkedList:
    string = a.get(1); // Get (typed) object at location 1
    i = a.indexOf("1"); // Tell index of object
    bool = a.isEmpty(); // Any elements inside?
    iterator = a.iterator(); // Ordinary Iterator
    listIterator = a.listIterator(); // ListIterator
    listIterator = a.listIterator(3); // Start at loc 3
    i = a.lastIndexOf("1"); // Last match
    a.remove(1); // Remove location 1
    a.remove("3"); // Remove this object
    a.set(1, "y"); // Set location 1 to "y"
    // Keep everything that'string in the argument
    // (the intersection of the two sets):
    a.retainAll(FlyweightMap.names(25));
    // Remove everything that'string in the argument:
    a.removeAll(FlyweightMap.names(25));
    i = a.size(); // How big is iterator?
    a.clear(); // Remove all elements
  }
  private static void iterMotion(List<String> a) {
    ListIterator<String> it = a.listIterator();
    bool = it.hasNext();
    bool = it.hasPrevious();
    string = it.next();
    i = it.nextIndex();
    string = it.previous();
    i = it.previousIndex();
  }
  private static void iterManipulation(List<String> a) {
    ListIterator<String> it = a.listIterator();
    it.add("47");
    // Must move to an element after add():
    it.next();
    // Remove the element after the newly produced one:
    it.remove();
    // Must move to an element after remove():
    it.next();
    // Change the element after the deleted one:
    it.set("47");
  }
  private static void testVisual(List<String> a) {
    LOGGER.info("Original list: {}", a);
    List<String> b = FlyweightMap.names(25);
    LOGGER.info("bool = " + b);
    a.addAll(b);
    a.addAll(b);
    LOGGER.info("Original list + 2 x addAll(list): {}", a);
    // Insert, remove, and replace elements
    // using a ListIterator:
    ListIterator<String> x = a.listIterator(a.size()/2);
    x.add("one");
    LOGGER.info("Add 'one': {}", a);
    LOGGER.info("ListIterator.next(): {}", x.next());
    x.remove();
    LOGGER.info("ListIterator.next(): {}", x.next());
    x.set("47");
    LOGGER.info("ListIterator.set('47'): {}", a);
    // Traverse the list backwards:
    x = a.listIterator(a.size());
    String traversed = "";
    while(x.hasPrevious())
      traversed += x.previous() + " ";
    LOGGER.info("Traverse: {}", traversed);
    LOGGER.info("testVisual finished");
    LOGGER.info("---------------------------");
  }
  // There are some things that only LinkedLists can do:
  private static void testLinkedList() {
    LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.addAll(FlyweightMap.names(25));
    LOGGER.info("Original: {}", linkedList);
    // Treat iterator like a stack, pushing:
    linkedList.addFirst("one");
    linkedList.addFirst("two");
    LOGGER.info("addFirst(): {}", linkedList);
    // Like "peeking" at the top of a stack:
    LOGGER.info("Like \"peeking\" at the top of a stack: {}", linkedList.getFirst());
    // Like popping a stack:
    LOGGER.info("Like popping a stack: {}", linkedList.removeFirst());
    LOGGER.info("Like popping a stack: {}", linkedList.removeFirst());
    // Treat iterator like a queue, pulling elements
    // off the tail end:
    LOGGER.info("removeLast(): {}", linkedList.removeLast());
    LOGGER.info("Finally: {}", linkedList);
    LOGGER.info("------------------------");
  }
  public static void main(String[] args) {
    // Make and fill a new list each time:
    basicTest(
      new LinkedList<String>(FlyweightMap.names(25)));
    basicTest(
      new ArrayList<String>(FlyweightMap.names(25)));
    iterMotion(
      new LinkedList<String>(FlyweightMap.names(25)));
    iterMotion(
      new ArrayList<String>(FlyweightMap.names(25)));
    iterManipulation(
      new LinkedList<String>(FlyweightMap.names(25)));
    iterManipulation(
      new ArrayList<String>(FlyweightMap.names(25)));
    testVisual(
      new LinkedList<String>(FlyweightMap.names(25)));
    testLinkedList();
  }
} /* (Execute to see output) *///:~
