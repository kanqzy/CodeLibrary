package com;

import java.util.*;
import java.util.stream.Collectors;
/**
 * 文件比对类
 */
public class Compare {
    private static enum State {
        same, change, add
    }
    /**
     * 
     * 文件清单比对
     * 
     * @param list 当前清单
     * @param lastList 上次清单
     * @return
     */
    public static Comparsion compare(List<FileInfo> list, List<FileInfo> lastList) {

        List<String> adds = new ArrayList<>();
        List<String> sames = new ArrayList<>();
        List<String> changes = new ArrayList<>();
        // 同文件名
        for (FileInfo f : list) {
            Map<String, Object> map = getFileState(f, lastList);
            State state = (State) map.get("state");
            FileInfo fileInfo = (FileInfo) map.get("fileInfo");
            if (state == State.add)
                adds.add(f.name);
            else {
                if (state == State.same)
                    sames.add(f.name);
                else
                    changes.add(f.name);
            }
            lastList.remove(fileInfo);
        }
        // lastList剩余部分就是要删除的
        List<String> dels = lastList.stream().map(f -> f.name).collect(Collectors.toList());
        Comparsion comparsion = new Comparsion(adds, sames, changes, dels);
        return comparsion;
    }

    /**
     * 获取一个文件在清单中的状态
     * 
     * @param f
     * @param list2
     * @return
     */
    private static Map<String, Object> getFileState(FileInfo f, List<FileInfo> list2) {
        State state = State.add;
        FileInfo fileInfo = null;
        for (int i = 0; i < list2.size(); i++) {
            FileInfo f2 = list2.get(i);
            if (f.name.equals(f2.name) && f.lastModified == f2.lastModified) {
                state = State.same;
                fileInfo = f2;
                break;
            } else if (f.name.equals(f2.name) && f.lastModified != f2.lastModified) {
                state = State.change;
                fileInfo = f2;
                break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("state", state);
        map.put("fileInfo", fileInfo);
        return map;
    }

}