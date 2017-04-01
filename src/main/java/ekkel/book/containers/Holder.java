package ekkel.book.containers;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import ekkel.book.util.Generator;
import ekkel.book.util.RandomGenerator;

import java.util.Comparator;
import java.util.stream.Stream;

import static com.google.common.base.MoreObjects.*;

/**
 * Created by cresh on 01.04.17.
 */
class Holder implements Comparable<Holder> {
    private String first;
    private String second;

    Holder(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Holder that) {
        return ComparisonChain.start().compare(this.first, that.first, String::compareToIgnoreCase).result();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!Objects.equal(this.getClass(), obj.getClass()))
            return false;
        Holder that = (Holder) obj;

        return Objects.equal(this.first, that.first) && Objects.equal(this.second, that.second);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 37 * result + first.hashCode();
        result = 37 * result + second.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("First", first)
                .add("Second", second)
                .toString();
    }

    static Comparator<Holder> getSecondStringComparator() {
        return (holder1, holder2) -> ComparisonChain
                .start()
                .compare(holder1.second, holder2.second, String::compareToIgnoreCase)
                .result();
    }

    static Stream<Holder> getHolderStream() {
        final HolderGenerator generator = new HolderGenerator();
        return Stream.generate(generator::next);
    }

    static Stream<Holder> getHolderStream(int stringSize) {
        final HolderGenerator generator = new HolderGenerator(stringSize);
        return Stream.generate(generator::next);
    }

    static Generator<Holder> getHolderGenerator() {
        return new HolderGenerator();
    }

    static Generator<Holder> getHolderGenerator(int size) {
        return new HolderGenerator(size);
    }

    private static class HolderGenerator implements Generator<Holder> {
        private Generator<String> stringGenerator;

        private HolderGenerator(int size) {
            stringGenerator = RandomGenerator.getStringGenerator(size);
        }

        private HolderGenerator() {
            stringGenerator = RandomGenerator.getStringGenerator();
        }

        @Override
        public Holder next() {
            return new Holder(stringGenerator.next(), stringGenerator.next());
        }
    }
}
