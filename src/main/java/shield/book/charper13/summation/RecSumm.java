package shield.book.charper13.summation;

/**
 * Created by cresh on 15.04.16.
 */
class RecSumm {
    private int sum;
    private int numb;

    <T extends Number> RecSumm(T t) {
        numb = t.intValue();
        do {
            this.sum += (this.numb--);
        } while (numb>0);
    }


    public int getSum() {
        return sum;

    }
}



