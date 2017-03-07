package ekkel.book.typeinfo.init;

import java.util.Random;

/**
 * Created by cresh on 09.08.16.
 */
class ClassInitialization {
    static Random random = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {
        Class initable = InitialFirst.class;
        System.out.println("After create link to InitialFirst");
        System.out.printf("Not initialize InitialFirst: InitialFirst.statFFirst=%d\n",
                InitialFirst.statFFirst);
        System.out.printf("Initialize InitialFirst: InitialFirst.statFSec=%d\n",
                InitialFirst.statFSec);
        System.out.printf("Initialize InitialSec: InitialSec.statNonF=%d\n",
                InitialSec.statNonF);
        Class third = Class.forName("ekkel.book.typeinfo.init.InitialThrird");
        System.out.println("After link to InitialThrird has been create");
        System.out.println(InitialThrird.staticNonFinal);
    }
}
