# 文档
## 名称
java IDE工程生成工具

## 描述
支持在vscode上编写的java代码生成eclipse工程,IDEA工程。

## 使用
启动后弹框选择config.json配置

json填写格式
```
{
    "name":"项目名",
    "type":"生成工程类型(applicaiton,web)",
    "dir":"项目目录",
    "dest":"生成目录",
    "ide":"开发工具(eclipse,idea)",
    "structureMap":{
        //项目相对路径
        "src":"代码路径",
        "lib":"依赖包路径"，
        "WebContent":"",
        "WEB-INF":""
    }
}
```