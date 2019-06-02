package com.artemsirosh.tij.generics.bank;

import com.artemsirosh.tij.generics.Generators;

import java.util.*;

/**
 * Created by cresh on 28.08.16.
 */
class BankTeller {
    private static void serve(Teller teller, Customer customer) {
        System.out.println(teller + " serves " + customer);
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        Queue<Customer> line = new LinkedList<>();
        Generators.fill(line,Customer.generator(),15);
        List<Teller> tellers = new ArrayList<>();
        Generators.fill(tellers,Teller.generator,4);
        for (Customer customer : line)
            serve(tellers.get(random.nextInt(tellers.size())),customer);
    }
}
