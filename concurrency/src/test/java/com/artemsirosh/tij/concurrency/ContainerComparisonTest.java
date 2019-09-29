package com.artemsirosh.tij.concurrency;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created at 28-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ContainerComparisonTest {

    @Test
    void shouldRunComparison() throws InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        final SynchronizedArrayListComparison comparison = new SynchronizedArrayListComparison(
                2, 2, executor::execute
        );

        comparison.run();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }

    @Test
    void asciiTableTest() {
        final AsciiTable table = new AsciiTable();
        table.addStrongRule();
        table.addRow(null, null, null, "A container comparison").setTextAlignment(TextAlignment.CENTER);
        table.addRule();
        table.addRow("Container name", "Read time", "Write time", "Read & Write time").setTextAlignment(TextAlignment.CENTER);
        table.addRule();
        table.addRow("Container", 344351300, 397334100, 741685400);
        table.addStrongRule();
        table.addRow(null, "Average", null, 397334100);
        table.addStrongRule();
        table.getContext().setGrid(U8_Grids.borderStrongDoubleLight());
        System.out.println(table.render());
    }
}
