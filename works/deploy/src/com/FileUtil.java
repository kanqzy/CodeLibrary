package com;

import java.io.*;
import java.util.*;

/**
 * 文件操作
 */
public class FileUtil {

    /**
	 * 根据指定目录生成文件清单
	 * 
	 * @param list 文件清单
	 * @param dir 目录
	 */
	public static void makeFileList(List<FileInfo> list, String dir) {
		File[] files = new File(dir).listFiles();
		if (!(files == null || files.length == 0)) {
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				String filePath = dir + "\\" + f.getName();
				list.add(new FileInfo(filePath, f.lastModified()));
				if (f.isDirectory()) {
					makeFileList(list, filePath);
				}
			}
		}
	}

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
        Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        reader.close();
        return sb.toString();
    }

    public static void write(String path, String jsonStr) throws Exception {
        Writer write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        write.write(jsonStr);
        write.flush();
        write.close();
    }

}


