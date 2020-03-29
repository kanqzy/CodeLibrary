package com;

import java.util.Map;
import java.util.function.Function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**生成工程类型 */
enum ProjectType {
    web, application
}


/**开发工具 */
enum IdeType {
    eclipse, idea
}

/**项目结构 */
enum ProjectStructureType {
    src,lib,webContent,webInfo
}

/**
 * 项目结构路径映射
 */
class ProjectStructureMap {
    String src;
    String lib;
    String webContent;
    String webInfo;
}

/**
 * json配置信息
 * 
 */
public class Config {
    // 项目名
    String name;
    // 生成程序类型
    ProjectType type = ProjectType.application;
    // 项目目录
    String projectDir;
    // 生成目录
    String destDir;
    // 开发工具
    IdeType ideType = IdeType.eclipse;
    // 项目结构路径映射
    ProjectStructureMap structureMap;

    public static Config from(String configStr) throws Exception {
        Map<String, Object> map = new Gson().fromJson(configStr, new TypeToken<Map<String, Object>>() {
        }.getType());
        Config config = new Config();
        config.name = (String) map.get("name");
        config.type = ProjectType.valueOf((String) map.get("type"));
        config.projectDir = (String) map.get("dir");
        config.destDir = (String) map.get("dest");

        @SuppressWarnings("unchecked")
        Map<String, Object> map2 = (Map<String, Object>) map.get("structureMap");
        ProjectStructureMap structureMap = new ProjectStructureMap();
        // 转绝对路径
        Function<String, String> toPath = (str) -> config.projectDir + "\\" + str;
        String src = toPath.apply((String) map2.get("src"));
        String lib = toPath.apply((String) map2.get("lib"));
        String webContent = toPath.apply((String) map2.get("WebContent"));
        String webInfo = toPath.apply((String) map2.get("WEB-INF"));

        for (String path : new String[] { src, lib, webContent, webInfo }) {
            if (!FileUtil.exists(path))
                throw new Exception("项目结构路径不存在");
        }
        structureMap.src = src;
        structureMap.lib = lib;
        structureMap.webContent = webContent;
        structureMap.webInfo = webInfo;

        config.structureMap = structureMap;
        return config;
    }
}