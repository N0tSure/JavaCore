package com.artemsirosh.tij.generics.functional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.artemsirosh.tij.generics.functional.Functional.*;


/**
 * Created by cresh on 20.12.16.
 */
class FunctionalTest {
    private static class IntegerAdder implements Combiner<Integer> {
        public Integer combine(Integer x, Integer y) {
            return x + y;
        }
    }
    private static class IntegerSubtracter implements Combiner<Integer> {
        public Integer combine(Integer x, Integer y) {
            return x - y;
        }
    }
    private static class BigDecimalAdder implements Combiner<BigDecimal> {
        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }
    private static class BigIntegerAdder implements Combiner<BigInteger> {
        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }
    private static class AtomicLongAdder implements Combiner<AtomicLong> {
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            // Not clear whether this is meaningful:
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }
    // We can even make a UnaryFunction with an "ulp"
    // (Units in the last place):
    private static class BigDecimalUlp implements UnaryFunction<BigDecimal,BigDecimal> {
        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }
    private static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {
        private T bound;
        public GreaterThan(T bound) { this.bound = bound; }
        public boolean test(T x) {
            return x.compareTo(bound) > 0;
        }
    }
    private static class MultiplyingIntegerCollector implements Collector<Integer> {
        private Integer val = 1;
        public Integer function(Integer x) {
            val *= x;
            return val;
        }
        public Integer result() { return val; }
    }
    public static void main(String[] args) {
        // Generics, varargs & boxing working together:
        List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer result = reduce(li, new IntegerAdder());
        System.out.println(" 1#: " + result);

        result = reduce(li, new IntegerSubtracter());
        System.out.println(" 2#: " + result);

        System.out.println(" 3#: " + filter(li, new GreaterThan<Integer>(4)));

        System.out.println(" 4#: " + forEach(li,
                new MultiplyingIntegerCollector()).result());

        System.out.println(" 5#: " + forEach(filter(li, new GreaterThan<Integer>(4)),
                new MultiplyingIntegerCollector()).result());

        MathContext mc = new MathContext(7);
        List<BigDecimal> lbd = Arrays.asList(
                new BigDecimal(1.1, mc), new BigDecimal(2.2, mc),
                new BigDecimal(3.3, mc), new BigDecimal(4.4, mc));
        BigDecimal rbd = reduce(lbd, new BigDecimalAdder());
        System.out.println(" 6#: " + rbd);

        System.out.println(" 7#: " + filter(lbd,
                new GreaterThan<BigDecimal>(new BigDecimal(3))));

        // Use the prime-generation facility of BigInteger:
        List<BigInteger> lbi = new ArrayList<BigInteger>();
        BigInteger bi = BigInteger.valueOf(11);
        for (int i = 0; i < 11; i++) {
            lbi.add(bi);
            bi = bi.nextProbablePrime();
        }
        System.out.println(" 8#: " + lbi);

        BigInteger rbi = reduce(lbi, new BigIntegerAdder());
        System.out.println(" 9#: " + rbi);
        // The sum of this list of primes is also prime:
        System.out.println("10#: " + rbi.isProbablePrime(5));

        List<AtomicLong> lal = Arrays.asList(
                new AtomicLong(11), new AtomicLong(47),
                new AtomicLong(74), new AtomicLong(133));
        AtomicLong ral = reduce(lal, new AtomicLongAdder());
        System.out.println("11#: " + ral);

        System.out.println("12#: " + transform(lbd,new BigDecimalUlp()));
    }
}
