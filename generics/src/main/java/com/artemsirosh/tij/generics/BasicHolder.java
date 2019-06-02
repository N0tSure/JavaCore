package com.artemsirosh.tij.generics;//: generics/BasicHolder.java

class BasicHolder<T> {
  private T element;
  void set(T arg) { element = arg; }
  T get() { return element; }
  void f() {
    System.out.println(element.getClass().getSimpleName());
  }
} ///:~
