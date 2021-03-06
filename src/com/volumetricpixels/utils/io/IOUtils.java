package com.volumetricpixels.utils.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.sql.Connection;

/**
 * @author VolumetricPixels
 */
public class IOUtils {
    /**
     * No instances
     */
    private IOUtils() {
    }

    public static void closeQuietly(Closeable toClose) {
        try {
            if (toClose != null) {
                toClose.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeSQL(Connection toClose) {
        try {
            if (toClose != null) {
                toClose.close();
            }
        } catch (Exception e) {
        }
    }

    public static void disconnect(HttpURLConnection toClose) {
        try {
            if (toClose != null) {
                toClose.disconnect();
            }
        } catch (Exception e) {
        }
    }

    public static OutputStream writeObjectToOS(OutputStream os, Object obj) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(obj);
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

        return os;
    }

    public static Object readObjectFromIS(InputStream is) {
        try {
            ObjectInputStream in = new ObjectInputStream(is);
            Object result = in.readObject();
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Uses Base64 along with Java's Serialization API to get an Object from a
     * Serialized String.
     * 
     * Please note the input String should be encoded in Base64. If you use the
     * toString(Serializable) method, the result will be encoded in Base64, so
     * as long as you got the Serialized String via toString(Serializable) in
     * the first place, everything should work.
     * 
     * You can cast the returned Object to whatever type the String was from in
     * the first place.
     * 
     * @param s
     *            The String to convert
     * @return The Object the String was converted to
     * 
     * @throws IOException
     *             If there is a read error
     * @throws ClassNotFoundException
     *             If the written Object's class is not found
     */
    public static Object fromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64Coder.decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Converts a Serializable Object into a Serialized String that is encoded
     * in Base64.
     * 
     * Can be used along with fromString(String) to store Objects as Strings and
     * later get them back. Please note this can be used to save to Files as
     * well, meaning it is a great way for storing Objects without loads of
     * manual parsing and creation of objects.
     * 
     * @param o
     *            The Serializable Object to stringify
     * @return The Serialized String
     *
     * @throws IOException
     *             When there is a write error
     */
    public static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return new String(Base64Coder.encode(baos.toByteArray()));
    }
}
