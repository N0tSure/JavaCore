package ekkel.book.generics.exception;

import java.util.List;

/**
 * Created by cresh on 17.12.16.
 */
class StringProcessor implements Processor<String,FirstFailure> {
    private static int count = 3;
    @Override
    public void process(List<String> resultCollector) throws FirstFailure {
        if (count-- > 1) {
            resultCollector.add("Hep!");
        } else {
            resultCollector.add("Ho!");
        }
        if (count < 0)
            throw new FirstFailure();
    }
}
