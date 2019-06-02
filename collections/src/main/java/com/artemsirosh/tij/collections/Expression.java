package com.artemsirosh.tij.collections;

import com.artemsirosh.tij.collections.StackTest.*;

/**
 * Created by NotSure on 03.03.16.
 */
public class Expression {
    static void printEx(String line) {
        Stack<Character> queue = new Stack<Character>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i)=='+') {
                queue.push(line.charAt(i+1));
            } else if (line.charAt(i)=='-') {
                System.out.print(queue.pop());
            } //else System.out.print(line.charAt(i));
        }
    }

    public static void main(String[] args) {
        String line = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        printEx(line);
    }
}
