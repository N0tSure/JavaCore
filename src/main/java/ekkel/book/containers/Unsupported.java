package ekkel.book.containers;//: containers/Unsupported.java
// Unsupported operations in Java containers.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

class Unsupported {
  private static final Logger LOGGER = LoggerFactory.getLogger(Unsupported.class);

  static void test(String msg, List<String> list) {
    LOGGER.info("--- " + msg + " ---");
    Collection<String> c = list;
    Collection<String> subList = list.subList(1,8);
    // Copy of the sublist:
    Collection<String> c2 = new ArrayList<String>(subList);
    try { c.retainAll(c2); } catch(Exception e) {
      LOGGER.info("retainAll(): {}", e.toString());
    }
    try { c.removeAll(c2); } catch(Exception e) {
      LOGGER.info("removeAll(): {}", e.toString());
    }
    try { c.clear(); } catch(Exception e) {
      LOGGER.info("clear(): {}", e.toString());
    }
    try { c.add("X"); } catch(Exception e) {
      LOGGER.info("add(): {}", e.toString());
    }
    try { c.addAll(c2); } catch(Exception e) {
      LOGGER.info("addAll(): {}", e.toString());
    }
    try { c.remove("C"); } catch(Exception e) {
      LOGGER.info("remove(): {}", e.toString());
    }
    // The List.set() method modifies the value but
    // doesn't change the size of the data structure:
    try {
      list.set(0, "X");
    } catch(Exception e) {
      LOGGER.info("List.set(): {}", e.toString());
    }
  }
  public static void main(String[] args) {
    List<String> list =
      Arrays.asList("A B C D E F G H I J K L".split(" "));
    test("Modifiable Copy", new ArrayList<String>(list));
    test("Arrays.asList()", list);
    test("unmodifiableList()",
      Collections.unmodifiableList(
        new ArrayList<String>(list)));
  }
} /* Output:
--- Modifiable Copy ---
--- Arrays.asList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
--- unmodifiableList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
List.set(): java.lang.UnsupportedOperationException
*///:~
