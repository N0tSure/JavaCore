package com.artemsirosh.tij.concurrency.greenhouse;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class BellTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Bing!");
    }
}
