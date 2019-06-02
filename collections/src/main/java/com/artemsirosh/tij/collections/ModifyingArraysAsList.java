package com.artemsirosh.tij.collections;

import java.util.*;

/**
 * Created by cresh on 19.03.16.
 */
public class ModifyingArraysAsList {
    public static void main(String[] args) {
        Random rnd = new Random(47);
        Integer[] nums = {0,1,2,3,4,5,6,7,8,9};

        System.out.println("До перемешивания:");
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(nums));
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
        System.out.println("\n\nПосле перемешивания:");
        System.out.println("Лист:");

        List<Integer> shuffled = Arrays.asList(nums);
        Collections.shuffle(shuffled,rnd);
        for (Integer integer : shuffled) {
            System.out.print(integer+" ");
        }
        System.out.println("\nМассив:");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println("\nОригинальный Лист:");
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
    }
}
