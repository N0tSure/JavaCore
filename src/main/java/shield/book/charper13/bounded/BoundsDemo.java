package shield.book.charper13.bounded;

/**
 * Created by cresh on 11.04.16.
 */
class BoundsDemo {
    static void printGens(NumericFns numericFns) {
        System.out.printf("Обратная величина %s : %.5f\n",
                numericFns.getClass().getCanonicalName(),numericFns.reciprocal());
        System.out.printf("Дробная часть %s : %.5f\n\n",
                numericFns.getClass().getSimpleName(), numericFns.fraction());
    }
    public static void main(String[] args) {
        NumericFns<Integer> integerNumericFns = new NumericFns<Integer>(58);
        printGens(integerNumericFns);
        System.out.println();

        NumericFns<Double> doubleNumericFns = new NumericFns<Double>(45.5);
        printGens(doubleNumericFns);

        //NumericFns<String> stringNumericFns = new NumericFns<String>("Error");
    }
}
