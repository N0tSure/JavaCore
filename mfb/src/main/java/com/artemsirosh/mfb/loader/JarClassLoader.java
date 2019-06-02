package com.artemsirosh.mfb.loader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cresh on 10.12.16.
 */
public class JarClassLoader extends ClassLoader {
    private Map<String, Class<?>> cache;
    private ClassLoader parent;
    private final String classpath;

    public JarClassLoader(ClassLoader parent, String classpath) {
        this.parent = parent;
        this.classpath = classpath;
        this.cache = new HashMap<>();
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result = cache.get(name);

        if (result==null)
            result = parent.loadClass(name);

        return result;
    }
}
