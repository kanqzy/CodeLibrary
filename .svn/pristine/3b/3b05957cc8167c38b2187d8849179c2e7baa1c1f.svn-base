package com;

import java.util.*;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
    private static final String execPath = getJarFilePath();
    private static Config config;

    public static void main(String[] args) throws Exception {
        config = readConfigXml();
        deploy();
    }

    public static void deploy() {
        // 初始化
        if (config.getInit() == true) {
            removeFileList();
            removeDeployDir();
        }
        // 遍历webDir获取当前清单
        List<FileInfo> fileList = getNowFileList();
        // deployDir不存在
        if (!new File(config.getDeployDir()).exists()) {
            FileUtil.copy(config.getDeployDir(), config.getWebDir());
        } else {
            String fileListPath = getFileListPath();
            // fileList 不存在
            if (!new File(fileListPath).exists()) {
                removeDeployDir();
                FileUtil.copy(config.getWebDir(), config.getDeployDir());
            } else {
                String jsonStr = FileUtil.read(fileListPath);
                List<FileInfo> lastFileList = new Gson().fromJson(jsonStr, new TypeToken<List<FileInfo>>() {
                }.getType());
                Comparsion comparsion = Compare.compare(fileList, lastFileList);
                // 根据比对复制删除
                compareCopyAndDelete(comparsion);
            }

        }
        // 写入当前清单
        FileUtil.write(getFileListPath(), new Gson().toJson(fileList));
        System.out.println("deploy finish ...");
    }

    public static void compareCopyAndDelete(Comparsion comparsion) {
        String webDir = config.getWebDir();
        String deployDir = config.getDeployDir();
        // 复制覆盖
        List<String> copys = new ArrayList<String>();
        copys.addAll(comparsion.adds);
        copys.addAll(comparsion.changes);
        for (String path : copys) {
            String newPath = path.replace(webDir, deployDir);
            if (new File(path).isDirectory())
                new File(newPath).mkdir();
            else {
                FileUtil.copyFile(path, newPath);
            }
        }
        // 删除
        List<String> dels = comparsion.dels;
        for (String path : dels) {
            String newPath = path.replace(webDir, deployDir);
            new File(newPath).delete();
        }
    }

    /**
     * 获取当前清单
     */
    public static List<FileInfo> getNowFileList() {
        // 遍历webDir生成当前清单
        List<FileInfo> fileList = new ArrayList<>();
        FileUtil.makeFileList(fileList, config.getWebDir());
        return fileList;
    }

    public static void removeFileList() {
        new File(getFileListPath()).delete();
    }

    public static void removeDeployDir() {
        new File(config.getDeployDir()).delete();
    }

    private static Config readConfigXml() throws Exception {

        return null;
    }

    /**
     * 获取清单文件路径
     * 
     * @return
     */
    private static String getFileListPath() {
        return execPath + "//" + "fileList.json";
    }

    /**
     * 获取jar包class路径
     * 
     * @return
     */
    private static String getJarFilePath() {
        String filePath = "";
        try {
            filePath = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
            filePath = filePath.substring(0, filePath.lastIndexOf("/"));
        } catch (Exception e) {
            System.out.println("getJarFilePath err :" + e.getMessage());
            e.printStackTrace();
        }
        return filePath;
    }

}