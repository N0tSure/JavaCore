package com.artemsirosh.tij.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * Created at 18-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int size) {
        super(size);
    }

    @Override
    public String toString() {
        final String result;
        if (isEmpty()) {
            result = "[Empty]";
        } else {
            result = stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
        }

        return result;
    }
}
