package com.artemsirosh.mfb.charper11.sync;

/**
 * Created by cresh on 24.03.16.
 */
class SumArray {
    private int sum;

     int getSum(int nums[]) {
        sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            System.out.printf("Actual sum for %s: %d\n",Thread.currentThread().getName(),sum);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println("Main thread interrupted.");
        }
        return sum;
    }
}
