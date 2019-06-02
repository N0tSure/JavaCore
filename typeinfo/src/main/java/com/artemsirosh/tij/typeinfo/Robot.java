package com.artemsirosh.tij.typeinfo;

import com.artemsirosh.tij.util.Null;

import java.util.List;

/**
 * Created by cresh on 15.08.16.
 */
interface Robot {
    String name();
    String model();
    List<Operation> operations();
    class Test {
        public static void test(Robot robot) {
            if (Null.class.isInstance(robot))
                System.out.println("[Null Robot]");
            System.out.println("Robot name: " + robot.name());
            System.out.println("Robot model: " + robot.model());
            for (Operation operation : robot.operations()) {
                System.out.println(operation.description());
                operation.command();
            }
        }
    }
}
