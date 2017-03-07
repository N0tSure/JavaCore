package shield.book.loader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cresh on 09.12.16.
 */
class ModuleEngine {
    private static void methods(Class<?> clazz, Object module)
            throws IllegalAccessException, InvocationTargetException {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.invoke(module);
        }
    }
    public static void main(String[] args) {
        ModuleLoader loader = new ModuleLoader(ClassLoader.getSystemClassLoader(),"lib");

        File path = new File("lib");
        String[] modules = path.list((dir, name) -> name.endsWith(".class"));
        if (modules==null) {
            System.out.println("Directory have no ClassFiles");
            System.exit(1);
        }

        for (String module : modules) {
            try {
                String name = module.replace(".class","");
                Class moduleClass = loader.findClass(name);
                Object execute = moduleClass.newInstance();
                System.out.println(execute.getClass().getSimpleName());
                methods(execute.getClass(), execute);

            } catch (ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException exc) {
                System.out.println(exc);
            }
        }
    }
}
