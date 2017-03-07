package ekkel.book.generics;//: generics/DogsAndRobots.java
// No latent typing in Java
import ekkel.book.typeinfo.pets.Dog;

class PerformingDog extends Dog implements Performs {
  public void speak() {
      System.out.println("Woof!");
  }
  public void sit() {
      System.out.println("Sitting");
  }
  public void reproduce() {}
}

class Robot implements Performs {
  public void speak() {
      System.out.println("Click!");
  }
  public void sit() {
      System.out.println("Clank!");
  }
  public void oilChange() {}
}	

class Communicate {
  static <T extends Performs>
  void perform(T performer) {
    performer.speak();
    performer.sit();
  }
}

public class DogsAndRobots {
  public static void main(String[] args) {
    PerformingDog d = new PerformingDog();
    Robot r = new Robot();
    Communicate.perform(d);
    Communicate.perform(r);
  }
} /* Output:
Woof!
Sitting
Click!
Clank!
*///:~
