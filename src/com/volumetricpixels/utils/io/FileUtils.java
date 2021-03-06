package com.volumetricpixels.utils.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author VolumetricPixels
 */
public class FileUtils {
    public static final long ONE_KB = 1024;
    public static final long ONE_MB = ONE_KB * ONE_KB;
    public static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    public static File findFile(String path) {
        return new File(path);
    }

    public static boolean copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (srcFile.exists() == false) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' exists but is a directory");
        }
        if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        File parentFile = destFile.getParentFile();
        if (parentFile != null) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile + "' directory cannot be created");
            }
        }
        if (destFile.exists() && destFile.canWrite() == false) {
            throw new IOException("Destination '" + destFile + "' exists but is read-only");
        }
        return doCopyFile(srcFile, destFile, preserveFileDate);
    }

    private static boolean doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' exists but is a directory");
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fis);
        }

        if (srcFile.length() != destFile.length()) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
        }
        if (preserveFileDate) {
            destFile.setLastModified(srcFile.lastModified());
        }
        return true;
    }

    public static String getExtension(File f) {
        String[] split = f.getName().split(".");
        return split[split.length - 1];
    }

    public static String getExtension(String filePath) {
        return getExtension(findFile(filePath));
    }

    public static boolean writeFile(String out, String contents) {
        return writeFile(findFile(out), contents);
    }

    public static boolean writeFile(File out, String contents) {
        boolean result = false;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(out));
            writer.write(contents);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignore) {
                }
            }
        }
        return result;
    }

    public static boolean clearContents(File f) {
        return delete(f) && createIfNotExists(f);
    }

    public static boolean createIfNotExists(File f) {
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean mkdirsIfNotExists(File f) {
        if (!f.exists()) {
            return f.mkdirs();
        }
        return true;
    }

    public static boolean delete(String file) {
        return delete(findFile(file));
    }

    public static boolean delete(File f) {
        return f.delete();
    }

    public static OutputStream writeToOS(OutputStream writeTo, File readFrom) {
        String s = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(readFrom));
            while ((s = br.readLine()) != null) {
                writeTo.write(s.getBytes());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(br);
        }
        return writeTo;
    }

    public static List<String> readLines(File f) {
        List<String> result = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.add(s);
            }
            br.close();
        } catch (IOException e) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ignore) {
                }
            }
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> readLines(String filePath) {
        return readLines(findFile(filePath));
    }

    public static boolean exists(String file) {
        return findFile(file).exists();
    }

    /**
     * Download a File
     * 
     * @param url
     * @param output
     * @return the File that was downloaded
     */
    public static File downloadFile(URL url, File output) {
        BufferedInputStream in = null;
        BufferedOutputStream bout = null;
        try {
            in = new BufferedInputStream(url.openStream());
            FileOutputStream fos = new FileOutputStream(output);
            bout = new BufferedOutputStream(fos, 1024);
            byte[] data = new byte[1024];
            int x = 0;
            while ((x = in.read(data, 0, 1024)) >= 0) {
                bout.write(data, 0, x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException ignore) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                }
            }
        }
        return output;
    }

    public static void writeObjectToFile(File file, Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObjectFromFile(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object result = in.readObject();
            in.close();
            fileIn.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
