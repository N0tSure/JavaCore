package ekkel.book.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cresh on 15.12.16.
 */
class ByteSet {
    private Byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9 };
    Set<Byte> byteSet = new HashSet<Byte>(Arrays.asList(bytes));
}
