package shield.book.charper13;

import java.util.Random;
/**
 * Created by cresh on 13.04.16.
 */
class GenericMethodDemo {
    static <T extends Comparable<T>, V extends T> boolean arrayEq(T[] x,V[] y) {
        if (x.length!=y.length) return false;
        for (int i = 0; i < x.length; i++) {
            if (!x[i].equals(y[i])) return false;
        }
        return true;
    }

    static Integer[] integerCreate() {
        Random random = new Random();
        Integer[] integers = new Integer[5];
        for (int i = 0; i < integers.length; i++) {
            integers[i]=random.nextInt(5);
        }
        return integers;
    }

    static Double[] doublesCreate() {
        Random random = new Random();
        Double[] integers = new Double[5];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextDouble();
        }
        return integers;
    }

    public static void main(String[] args) {
        Integer[] integers = null;
        Integer[] template =  integerCreate();
        Double[] doubles = doublesCreate();
        arrayEq(doubles,doublesCreate());
        float equalsCounter = 0;

        System.out.print("Массив-образец: ");
        for (Integer integer : template) {
            System.out.print(integer + " ");
        }
        System.out.println();


        for (int i = 0; i < 100000; i++) {
            integers = integerCreate();
            //System.out.print("Сравниваемый массив: ");
            //for (Integer integer : integers) {
            //    System.out.print(integer + " ");
            //}

            if (arrayEq(template,integers)) {
                System.out.println("Массивы template и integers совпадают");
                equalsCounter++;
            }
            //System.out.printf("\nМассивы template и integers %s\n",
            //       arrayEq(template,integers) ? "совпадают" : "различаются");
        }
        System.out.printf("Совпадений %s : %.3f ",equalsCounter,(equalsCounter/100000)*100);
    }
}
