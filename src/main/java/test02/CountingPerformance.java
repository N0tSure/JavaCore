package test02;

import ekkel.book.io.LargeSampleCreation;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     Created on 12 Jun, 2017.
 *     Character counting performance benchmarks with {@code OpenJDK JMH}
 * </p>
 * <p>
 *     There two ways of measurement:
 *     <ul>
 *         <li>
 *             Small text {@code NOT_ASCII_SAMPLE} file with UTF-8 text
 *         </li>
 *         <li>
 *             Large text file {@code LARGE_SAMPLE} file with UTF-8 text
 *             <em>NOTE:</em> You should generate this file, for this use
 *             {@link LargeSampleCreation#realSampleCreation()}
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     <em>Note: </em>{@link QuickCounter} have some issues with closing files.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
public class CountingPerformance {

    private final static String NOT_ASCII_SAMPLE = "/home/train/Core/src/main/resources/n_asci.sample";
    private final static String LARGE_SAMPLE = "/home/train/Core/src/main/resources/large.sample";
    private final static char[] CHARS = "onvMus는절에수ступТвон".toCharArray();

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void referenceCounterSmallNoASCIISample() throws Exception {
        CharacterCounter counter = new ObviousCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, NOT_ASCII_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void quickCounterSmallNoASCIISample() throws Exception {
        CharacterCounter counter = new QuickCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, NOT_ASCII_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void mappingCounterSmallNoASCIISample() throws Exception {
        CharacterCounter counter = new FileMappingCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, NOT_ASCII_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void nonWrappingCounterSmallNoASCIISample() throws Exception {
        CharacterCounter counter = new NonWrappingCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, NOT_ASCII_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void referenceCounterLargeSample() throws Exception {
        CharacterCounter counter = new ObviousCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, LARGE_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void quickCounterLargeSample() throws Exception {
        CharacterCounter counter = new QuickCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, LARGE_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void mappingCounterLargeSample() throws Exception {
        CharacterCounter counter = new FileMappingCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, LARGE_SAMPLE);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void nonWrappingCounterLargeSample() throws Exception {
        CharacterCounter counter = new NonWrappingCounter();

        for (char character : CHARS) {
            counter.countCharacters(character, LARGE_SAMPLE);
        }
    }

}
