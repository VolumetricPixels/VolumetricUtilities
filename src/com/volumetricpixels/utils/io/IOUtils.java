package com.volumetricpixels.utils.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;

import javax.net.ssl.HttpsURLConnection;

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

    public static void disconnect(HttpsURLConnection toClose) {
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
}
