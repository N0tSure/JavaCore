package com.artemsirosh.tij.io;

import com.artemsirosh.tij.util.OSExecute;
import org.junit.Test;

/**
 * Created on 29 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class OSExecuteTest {

    @Test
    public void executionTest() throws Exception {
        OSExecute.command("curl gole.om");
    }
}
