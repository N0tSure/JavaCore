package ekkel.book.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by cresh on 29.07.16.
 */
public class SimpleRead {
    public static BufferedReader input = new BufferedReader(new StringReader("Sir Robin of Camelot\n" +
            "22 1,61803"));


    public static void main(String[] args) throws IOException{
        System.out.println("What is your name?");
        String name = input.readLine();
        System.out.println(name);
        System.out.println("How old are you? What's your favorite double?");
        System.out.println("(input: <age> <double>)");
        String numbers = input.readLine();
        System.out.println(numbers);
        String[] array = numbers.split(" ");
        int age = new Integer(array[0]);
        double favorite = new Double(array[1]);
        System.out.printf("Hi %s.\nIn 5 years you will be %d.\nMy favorite double is %f",name,age+5,favorite/2);
    }
}
