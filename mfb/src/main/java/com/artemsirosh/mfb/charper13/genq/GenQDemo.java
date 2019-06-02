package com.artemsirosh.mfb.charper13.genq;

import java.util.Random;

/**
 * Created by cresh on 15.04.16.
 */
public class GenQDemo {
    /*static Random rnd = new Random();
    static int numsGenerator(int border) {
        return rnd.nextInt(border);
    }*/
    public static <T extends Integer>void fill(IGenQ<Integer> queue) throws QueueFullException, QueueEmptyException {
        Random random = new Random();
        Integer k;
        for (int i = 0; i < 5; i++) {
            k=random.nextInt(10);
            System.out.printf("Добавление %d\n",k);
            queue.put(k);
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            k=queue.get();
            System.out.printf("Получено число %d из очереди.\n",k);
        }
        System.out.println();
    }

    public static <T extends Double>void dfill(IGenQ<Double> queue) throws QueueFullException, QueueEmptyException {
        Random random = new Random();
        Double k;
        for (int i = 0; i < 5; i++) {
            k=random.nextDouble();
            System.out.printf("Добавление %.5f\n",k);
            queue.put(k);
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            k=queue.get();
            System.out.printf("Получено число %.5f из очереди.\n",k);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] i = new Integer[10];
        IGenQ<Integer> integerGenQueue = new GenQueue<Integer>(i);
        IGenQ<Integer> intDQ = new GenDynQueue<Integer>(10);
        IGenQ<Integer> intCQ = new GenCircQueue<Integer>(10);

        try {
            fill(integerGenQueue);
            fill(intDQ);
            fill(intCQ);
        } catch (QueueEmptyException exc) {
            System.out.println(exc);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }

        IGenQ<Double> dGDQ= new GenDynQueue<Double>(10);
        IGenQ<Double> dGCQ= new GenCircQueue<Double>(10);

        try {
            dfill(dGDQ);
            dfill(dGCQ);
        } catch (QueueEmptyException exc) {
            System.err.println(exc);
        } catch (QueueFullException exc) {
            System.err.println(exc);
        }

        /*int k;

        for (int j = 0; j < 5; j++) {
            k=numsGenerator(10);
            System.out.printf("Добавление %d\n",k);
            try {
                queue.put(k);
            } catch (QueueFullException exc) {
                System.out.println(exc);
            }
        }
        System.out.println();

        for (int j = 0; j < 5; j++) {
            try {
                k=queue.get();
                System.out.printf("Получено число %d типа %s из очереди.\n",k,
                        queue.getClass().getName().substring((queue.getClass().getName().length()-8)));
            } catch (QueueEmptyException exc) {
                System.out.println(exc);
            }

        }
        System.out.println();

        Double[] db = new Double[10];
        IGenQ<Double> dbQueue = new GenQueue<Double>(db);
        Double aDouble;

        for (int j = 0; j < 5; j++) {
            aDouble= (double) numsGenerator(10)/5;
            System.out.printf("Добавление %f\n",aDouble);
            try {
                dbQueue.put(aDouble);
            } catch (QueueFullException exc) {
                System.out.println(exc);
            }
        }
        System.out.println();

        for (int j = 0; j < 5; j++) {
            try {
                aDouble=dbQueue.get();
                System.out.printf("Получено число %f типа %s из очереди.\n",aDouble,
                        queue.getClass().getName().substring((queue.getClass().getName().length()-8)));
            } catch (QueueEmptyException exc) {
                System.out.println(exc);
            }

        }*/

    }
}
