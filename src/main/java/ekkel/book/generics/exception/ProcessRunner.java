package ekkel.book.generics.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 17.12.16.
 */
class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {
    List<T> processAll() throws E {
        List<T> resultCollector = new ArrayList<T>();
        for(Processor<T,E> processor : this)
            processor.process(resultCollector);
        return resultCollector;
    }
}
