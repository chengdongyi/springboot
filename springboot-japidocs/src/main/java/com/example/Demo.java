package com.example;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

public class Demo {

    public static void main(String[] args) {

        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("E:\\Workspaces\\springboot\\springboot-japidocs");
        // 项目名称
        config.setProjectName("Api 文档 Demo");
        // 声明该 Api 的版本
        config.setApiVersion("v1.0");
        // 生成 Api 文档所在目录
        config.setDocsPath("D:\\test\\apidocs");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 生成 Markdown 文档
        config.addPlugin(new MarkdownDocPlugin());
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }

}
