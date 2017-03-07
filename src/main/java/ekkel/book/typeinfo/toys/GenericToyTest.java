package ekkel.book.typeinfo.toys;

/**
 * Created by cresh on 09.08.16.
 */
class GenericToyTest {
    public static void main(String[] args) throws Exception{
        Class<FancyToy> fancyToyClass = FancyToy.class;
        FancyToy fancyToy = fancyToyClass.newInstance();
        Class<? super FancyToy> up = fancyToyClass.getSuperclass();
//        Class<Toy> up2 = fancyToyClass.getSuperclass();
        Object o = up.newInstance();
        Toy toy = fancyToy;
        FancyToy casted = fancyToyClass.cast(toy);

    }
}
