package ekkel.book.typeinfo.filled;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 09.08.16.
 */
class FilledList<T> {
    private Class<T> type;
    FilledList(Class<T> type) {
        this.type = type;
    }
    public List<T> create(int elementsAmount) {
        List<T> result = new ArrayList<>();
        try {
            for (int i = 0; i < elementsAmount; i++) {
                result.add(type.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
        System.out.println(filledList.create(15));
    }
}
