package com.artemsirosh.tij.containers;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cresh on 02.04.17.
 */
public class SimpleRule implements TestRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRule.class);

    @Override
    public Statement apply(final Statement base,final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                LOGGER.info("Before method {}, test count: {}", description.getMethodName(), description.testCount());
                base.evaluate();
                LOGGER.info("After method {}, test count: {}", description.getMethodName(), description.testCount());
            }
        };
    }
}
