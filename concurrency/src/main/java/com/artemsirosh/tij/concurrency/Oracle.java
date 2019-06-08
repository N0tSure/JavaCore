package com.artemsirosh.tij.concurrency;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Oracle {
    private int answer;
    private boolean isReady;

    synchronized void ask() {
        isReady = true;
        answer = 42;
    }

    synchronized String getAnswer() {
        final String result;
        if (isReady)
            result = "Answer is " + answer;
        else
            result = "Answer not ready";

        forgetAnswer();
        return result;
    }

    private void forgetAnswer() {
        answer = 0;
        isReady = false;
    }
}
