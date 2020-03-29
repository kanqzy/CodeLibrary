package com;

/**
 * 
 * 文件清单 
 * 检测文件变化
 */
public class FileInfo {
    // 文件全路径名
    public String name;
    // 最后一次被修改时间
    public long lastModified;

    public FileInfo(String name, long lastModified) {
        this.name = name;
        this.lastModified = lastModified;
    }
}