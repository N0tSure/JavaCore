package ekkel.book.containers;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cresh on 02.04.17.
 */
public class SimpleTestWatcher extends TestWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTestWatcher.class);

    @Override
    protected void starting(Description description) {
        LOGGER.info("Before test {}, class {}", description.getMethodName(), description.getClassName());
    }

    @Override
    protected void succeeded(Description description) {
        LOGGER.info("Succeed method {}, class {}", description.getMethodName(), description.getClassName());
    }

    @Override
    protected void finished(Description description) {
        LOGGER.info("Finished method {}, class {}", description.getMethodName(), description.getClassName());
    }

}
