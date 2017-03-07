package shield.book.charper13.summation;

/**
 * Created by cresh on 15.04.16.
 */
class Summation {
    private int sum;
    <T extends Number> Summation(T t) {
        sum=0;
        for (int i = 0; i <= t.intValue(); i++) {
            sum+=i;
        }
    }

    int getSum() {
        return sum;
    }
}
