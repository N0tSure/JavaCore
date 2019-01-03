package ekkel.book.io;

import ekkel.book.util.OSExecute;
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
