package com.artemsirosh.mfb.loader;

import java.io.*;

/**
 * Created by cresh on 09.12.16.
 */
public class ModuleLoader extends ClassLoader {
    private final String libPath;

    ModuleLoader(ClassLoader parent, String libPath) {
        super(parent);
        this.libPath = libPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File moduleBinaryFile = new File(libPath + "/" + name + ".class");
        if (!moduleBinaryFile.exists())
            throw new ClassNotFoundException(
                String.format("File %s.class not exist",name));

        try {
            byte[] binary = readClassFromDisk(moduleBinaryFile);
            return defineClass(name,binary,0, binary.length);
        } catch (IOException exc) {
            return super.findClass(name);
        }
    }

    private byte[] readClassFromDisk(File classFile) throws IOException {
        long fileLength = classFile.length();
        if (fileLength >= Integer.MAX_VALUE)
            throw new IOException("File too large");

        byte[] result = new byte[(int) fileLength];
        try (InputStream inputStream = new FileInputStream(classFile)) {
            if (fileLength < inputStream.read(result))
                throw new IOException("Could not completely read file " + classFile.getName());
        }
        return result;
    }
}
