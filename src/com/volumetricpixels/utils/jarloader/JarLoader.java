package com.volumetricpixels.utils.jarloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoader {
    private ClassLoader loader = null;

    public JarLoader(File jar) {
        try {
            this.loader = new URLClassLoader(new URL[] { jar.toURI().toURL() });
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid File supplied as a jar file!");
        }
    }

    public JarLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public Class<?> loadClass(String pathToMainClass) throws JarLoadingFailedException {
        try {
            return loader.loadClass(pathToMainClass);
        } catch (ClassNotFoundException e) {
            throw new JarLoadingFailedException("Could not find a class by the specified name in the given jar!", e);
        }
    }
}
