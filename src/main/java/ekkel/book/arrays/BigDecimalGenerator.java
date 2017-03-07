package ekkel.book.arrays;

import ekkel.book.util.Generator;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by cresh on 07.02.17.
 */
public class BigDecimalGenerator implements Generator<BigDecimal> {
    private Random random;
    private BigDecimal base;

    public BigDecimalGenerator() {
        this.random = new Random();
        this.base = new BigDecimal(Long.MAX_VALUE);
    }

    @Override
    public BigDecimal next() {
        return this.base.multiply(new BigDecimal(random.nextLong()));
    }
}
