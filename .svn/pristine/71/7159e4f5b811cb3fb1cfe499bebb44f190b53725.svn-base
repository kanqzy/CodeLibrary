package com;

import java.io.File;
import java.util.*;

public class ProjectMaker {
    String execPath = Main.execPath;
    Config config;

    public ProjectMaker(Config config) {
        this.config = config;
    }

    void make() throws Exception {
        copyTemplate();
        rewriteTemplate();
        fillTemplate();
        System.out.println("project make finish ...");
    }

    /** copy模板 */
    public void copyTemplate() throws Exception {
        String templateDir = getTemplateDir();
        String destDir = config.destDir + "\\" + config.name;
        File dest = new File(destDir);
        if (dest.exists())
            dest.delete();
        FileUtil.copy(templateDir, destDir);
    }

    /** 重写模板 */
    public void rewriteTemplate() throws Exception {
        IdeType ideType = config.ideType;
        if (ideType == IdeType.eclipse) {
            String destProjectDir = getDestProjectDir();
            List<String> replaceFileNames = new ArrayList<>();
            replaceFileNames.add(destProjectDir + "\\" + ".project");
            if (config.type == ProjectType.web) {
                replaceFileNames.add(destProjectDir + "\\" + ".settings\\org.eclipse.wst.common.component");
                replaceFileNames.add(destProjectDir + "\\" + "WebContent\\WEB-INF\\web.xml");
            }
            // 模板名称关键词替换
            for (String fileName : replaceFileNames) {
                String content = FileUtil.read(fileName);
                content = content.replace(config.type.toString() + "-model", config.name);
                FileUtil.write(fileName, content);
            }
        }
    }

    /** 填充模板 */
    public void fillTemplate() throws Exception {
        ProjectStructureMap structureMap = config.structureMap;
        IdeType ideType = config.ideType;
        if (ideType == IdeType.eclipse) {
            FileUtil.copy(structureMap.src, getDestProjectPath(ProjectStructureType.src));
            FileUtil.copy(structureMap.lib, getDestProjectPath(ProjectStructureType.lib));
            if (config.type == ProjectType.web) {
                FileUtil.copy(structureMap.webContent, getDestProjectPath(ProjectStructureType.webContent));
                FileUtil.copy(structureMap.webInfo, getDestProjectPath(ProjectStructureType.webInfo));
            }
        }
    }

    /** 模板目录 */
    private String getTemplateDir() {
        String ide = config.ideType.toString();
        String type = config.type.toString();
        return getProjectDir() + "\\resource\\template\\" + ide + "\\" + type + "-model";
    }

    /** 项目根目录 */
    private String getProjectDir() {
        return new File(execPath).getParent();
    }

    /** 生成项目根目录 */
    private String getDestProjectDir() {
        return config.destDir + "\\" + config.name;
    }

    /** 生成项目结构路径 */
    private String getDestProjectPath(ProjectStructureType type) {
        String projectDir = getDestProjectDir();
        switch (type) {
            case src:
                return projectDir + "\\" + "src";
            case lib:
                return projectDir + "\\" + "WebContent\\WEB-INF\\lib";
            case webContent:
                return projectDir + "\\" + "WebContent";
            case webInfo:
                return projectDir + "\\" + "WebContent\\WEB-INF";
            default:
                return "";
        }
    }
}