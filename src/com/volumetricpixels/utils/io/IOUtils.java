package com.volumetricpixels.utils.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.nio.channels.Channel;
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

    public static void closeQuietly(Reader toClose) {
        try {
            if (toClose != null) {
                toClose.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeQuietly(Writer toClose) {
        try {
            if (toClose != null) {
                toClose.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeQuietly(Channel toClose) {
        try {
            if (toClose != null) {
                toClose.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeQuietly(InputStream toClose) {
        try {
            if (toClose != null) {
                toClose.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeQuietly(OutputStream toClose) {
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
}
