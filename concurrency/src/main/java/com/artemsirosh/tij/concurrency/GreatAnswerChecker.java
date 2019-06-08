package com.artemsirosh.tij.concurrency;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class GreatAnswerChecker implements Runnable {

    private final int id;
    private long answerCount;
    private long strangeAnswerCount;
    private final Oracle oracle;

    GreatAnswerChecker(int id, Oracle oracle) {
        this.id = id;
        this.oracle = oracle;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            oracle.ask();
            final String answer = oracle.getAnswer();
            answerCount++;
            if (answer.contains("is") && !answer.contains("42")) {
                strangeAnswerCount++;
            }
        }

        System.out.println(
                this + " summary: all answers (" + answerCount + "), strange answers(" + strangeAnswerCount + ")."
        );
    }

    @Override
    public String toString() {
        return "Checker #" + id;
    }
}
