package com.artemsirosh.mfb.charper13.genif;

/**
 * Created by cresh on 15.04.16.
 */
class Main {
    public static void main(String[] args) {
        Integer[] nums = {1,2,3,3,4,5,6};
        MyClass<Integer> ms = new MyClass<Integer>(nums);
        int i;

        for (int j = 0; j < 10; j++) {
            i= (int)(Math.random()*10);
            System.out.printf("Число %d %s в массиве.\n",i,
                    ms.contains(i) ? "содержится" : "не содержится");
        }
    }
}
