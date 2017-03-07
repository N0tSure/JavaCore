package shield.book.lesson7.reflection;

import java.lang.reflect.Constructor;
import java.util.Formatter;

/**
 * Created by cresh on 11.07.16.
 */
class Clazz {
    private static Formatter formatter = new Formatter(System.out);

    private static void showClassParams(Object o) {
        Class clazz = o.getClass();
        formatter.format("Имя класса %s\nпакет %s\nмодификаторы %s\n",
                clazz.getName(),clazz.getPackage(),clazz.getModifiers());
    }

    private static Object dynamicObjectGenerator(Class clazz) throws InstantiationException,IllegalAccessException {
        return Model.class.newInstance();
    }

    private static void getConstructors(Class clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            System.out.println(constructor.getName());
        }
    }

    public static void main(String[] args) throws Exception {
        Model model = new Model();
        showClassParams(model);
        System.out.println("\nDynamic generate object:");
        showClassParams(dynamicObjectGenerator(Model.class));

        System.out.println();
        getConstructors(Model.class);
    }
}
