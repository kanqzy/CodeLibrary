package com;

import java.io.*;
import java.util.*;

/**
 * 文件操作
 */
public class FileUtil {

    /**
     * 
     * @param from 原目录
     * @param to   目标目录
     * @throws Exception
     */
    public static void copy(String from, String to) throws Exception {
        copy(from, to, null);
    }

    public static void copy(String from, String to, String[] excludes) throws Exception {
        File fromFile = new File(from);
        File toFile = new File(to);
        copy(fromFile, toFile, excludes);
    }

    public static void copy(File from, File to, String[] excludes) throws Exception {
        File[] fs = from.listFiles();    
        if (!to.exists())
            to.mkdir();

        for (int i = 0; i < fs.length; i++) {
            // 是否排除
            if (exists(excludes, fs[i].getName()))
                continue;
            if (fs[i].isDirectory()) {
                String dirname = fs[i].getName();
                String dirpath = to + "\\" + dirname;
                File f = new File(dirpath);
                if (!f.exists())
                    f.mkdir();
                copy(fs[i], f, excludes);
            } else {
                String filename = fs[i].getName();
                String filepath = to + "\\" + filename;
                File f = new File(filepath);
                if (!f.exists())
                    f.createNewFile();
                copyFile(fs[i], f);
            }
        }
    }

    /**
     * 文件复制
     * 
     * @param from
     * @param to
     * @throws Exception
     */
    public static void copyFile(String from, String to) throws Exception {
        File fromFile = new File(from);
        File toFile = new File(to);
        copyFile(fromFile, toFile);
    }

    public static void copyFile(File from, File to) throws Exception {
        FileInputStream fin = new FileInputStream(from);
        FileOutputStream fout = new FileOutputStream(to);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(fout);

        byte[] b = new byte[2048];
        int len = bin.read(b);
        while (len != -1) {
            bout.write(b, 0, len);
            len = bin.read(b);
        }

        bout.close();
        fout.close();
        bin.close();
        fin.close();
    }

    public static boolean exists(String[] filenames, String filename) {
        if (filenames == null || filenames.length == 0)
            return false;
        String str = Arrays.toString(filenames);
        if (str.indexOf(filename) == -1)
            return false;
        return true;
    }

    public static String read(String path) throws Exception {
        File file = new File(path);
        return read(file);
    }

    public static String read(File file) throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        reader.close();
        return sb.toString();
    }

    public static void write(String path, String content) throws Exception {
        Writer write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        write.write(content);
        write.flush();
        write.close();
    }

    public static boolean exists(String path) {
        if (new File(path).exists())
            return true;
        return false;
    }

    public static boolean rename(String oldPathName, String newPathName) throws Exception {
        return new File(oldPathName).renameTo(new File(newPathName));
    }

}
