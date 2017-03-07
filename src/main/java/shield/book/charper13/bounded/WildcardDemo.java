package shield.book.charper13.bounded;

/**
 * Created by cresh on 11.04.16.
 */
class WildcardDemo {
    static void print(NumericFns<? extends Number> first, NumericFns scnd) {
        System.out.printf("Сравнение %1$s и %2$s\n",first.getNum().toString(),scnd.getNum().toString());
        System.out.printf("Абсолютные значения %s.\n\n",first.absEquals(scnd) ? "совпадают" : "различаются");
    }
    public static void main(String[] args) {
        NumericFns<Integer> integerNumericFns = new NumericFns<Integer>(6);
        NumericFns<Double> doubleNumericFns = new NumericFns<Double>(-6.0);
        NumericFns<Long> longNumericFns = new NumericFns<Long>(5L);

        print(integerNumericFns,doubleNumericFns);
        print(integerNumericFns,longNumericFns);
        print(longNumericFns,doubleNumericFns);
        print(integerNumericFns,integerNumericFns);
    }
}
