package com;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件比对信息
 */
public class Comparsion {
   
    // 所有新增文件路径
    public List<String> adds;
    // 无变化
    public List<String> sames;
    // 变化
    public List<String> changes;
    // 删除
    public List<String> dels;

    public Comparsion(List<String> adds, List<String> sames, List<String> changes, List<String> dels) {
        if (adds == null)
            adds = new ArrayList<String>();
        if (sames == null)
            sames = new ArrayList<String>();
        if (changes == null)
            changes = new ArrayList<String>();
        if (dels == null)
            dels = new ArrayList<String>();
        this.adds = adds;
        this.sames = sames;
        this.changes = changes;
        this.dels = dels;
    }
 
}