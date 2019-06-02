package com.artemsirosh.tij.generics.exception;

/**
 * Created by cresh on 17.12.16.
 */
class ThrowGenericException {
    public static void main(String[] args) {
        ProcessRunner<String,FirstFailure> runner =
                new ProcessRunner<String,FirstFailure>();
        for(int i = 0; i < 3; i++)
            runner.add(new StringProcessor());
        try {
            System.out.println(runner.processAll());
        } catch(FirstFailure e) {
            System.out.println(e);
        }

        ProcessRunner<Integer,SecondFailure> runner2 =
                new ProcessRunner<Integer,SecondFailure>();
        for(int i = 0; i < 3; i++)
            runner2.add(new IntegerProcessor());
        try {
            System.out.println(runner2.processAll());
        } catch(SecondFailure e) {
            System.out.println(e);
        }
    }
}
