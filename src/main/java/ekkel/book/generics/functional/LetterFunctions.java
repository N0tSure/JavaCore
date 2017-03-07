package ekkel.book.generics.functional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cresh on 20.12.16.
 */
class LetterFunctions {
    private static class StringAdder implements Combiner<String> {
        @Override
        public String combine(String first, String second) {
            return first.concat(second);
        }
    }

    private static class StringMultiplier implements UnaryFunction<String, Integer> {
        private StringBuilder builder;
        public StringMultiplier(String value) {
            this.builder = new StringBuilder(value);
        }
        @Override
        public String function(Integer integer) {
            for (int i = 0; i < integer; i++) {
                builder.append(builder.toString());
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("A", "B", "C", "D", "E");
        System.out.println(Functional.reduce(strings, new StringAdder()));

    }
}
