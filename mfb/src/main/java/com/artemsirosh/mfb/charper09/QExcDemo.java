package com.artemsirosh.mfb.charper09;

/**
 * Created by cresh on 08.01.16.
 */
class QueueFullExc extends Exception {
    int size;

    public QueueFullExc(int size) {
        this.size = size;
    }

    public String toString() {
        return "\nQueue is full. Maximum size: " + size;
    }
}

class QueueEmptyExc extends Exception {
    public String toString() {
        return "\nQueue is empty.";
    }
}

class FixedQueue {
    private char q[];
    private int putloc, getloc;

    public FixedQueue(int size) {
        q = new char[size+1];
        putloc=getloc=0;
    }

    public void put(char ch) throws QueueFullExc {
        if (putloc==q.length-1) throw new QueueFullExc(q.length-1);
        putloc++;
        q[putloc]=ch;
    }

    public char get() throws QueueEmptyExc {
        if (getloc==putloc) throw new QueueEmptyExc();
        getloc++;
        return q[getloc];
    }
}
class QExcDemo {
    public static void main(String[] args) {
        FixedQueue ob = new FixedQueue(10);
        char ch;
        int i;

        try {
            for (i = 0; i < 11; i++) {
                System.out.println("Attempting save: " + (char) ('A' + i));
                ob.put((char) ('A'+i));
                System.out.println(" - OK");
            }
            System.out.println();
        } catch (QueueFullExc exc) {
            System.out.println(exc);
        }
        System.out.println();

        try {
            for (i = 0; i < 11; i++) {
                System.out.print("Obtain character: ");
                ch = ob.get();
                System.out.println(ch);
            }
        } catch (QueueEmptyExc exc) {
            System.out.println(exc);
        }
    }

}


