package com.artemsirosh.tij.typeinfo;

import com.artemsirosh.tij.util.Null;

import java.lang.reflect.Proxy;

/**
 * Created by cresh on 15.08.16.
 */
class NullRobot {
    public static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader(),
                new Class[]{Null.class,Robot.class},new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {
        Robot[] robots = {
                new SnowRemovalRobot("SnowBee"),
                newNullRobot(SnowRemovalRobot.class)
        };
        for (Robot robot : robots) {
            Robot.Test.test(robot);
        }

        //System.out.println(robots[1].);
    }
}
