package com.volumetricpixels.utils;

/**
 * @author VolumetricPixels
 */
public class Utilities {
    public static boolean classExists(String clazz) {
        try {
            Class.forName(clazz);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(String clazz) {
        try {
            return (Class<T>) Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
