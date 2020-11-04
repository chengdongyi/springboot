package com.example.utils;

import lombok.Cleanup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author chengdongyi
 * @description: Zip 工具类
 * @date 2020/10/29 13:58
 */
public class ZipUtil {

    private static Charset charset = Charset.forName("GBK");
    private static String dot = ".";

    /**
     * 功能描述: 压缩文件或目录
     * @author chengdongyi
     * @date 2020/11/4 11:16
     * @param file
     * @return void
     */
    public static void zip(String file) throws IOException {
        String zipFile = null;
        String zipSuffix = ".zip";
        if (file.contains(dot)) {
            String suffix = file.substring(file.lastIndexOf(dot));
            zipFile = file.replace(suffix, zipSuffix);
        } else {
            zipFile = file + zipSuffix;
        }
        File files = new File(file);
        if (files.isDirectory()) {
            zipdir(zipFile, file);
        } else {
            zip(zipFile, file);
        }
    }

    /**
     * 功能描述: 压缩文件
     * @author chengdongyi
     * @date 2020/11/4 11:15
     * @param zipFile zip 文件名
     * @param files   文件列表
     * @return void
     */
    public static void zip(String zipFile, String... files) throws IOException {

        @Cleanup ZipOutputStream output = new ZipOutputStream(new FileOutputStream(new File(zipFile)));
        byte[] data = new byte[1024];
        for (String path : files) {
            File file = new File(path);
            @Cleanup FileInputStream input = new FileInputStream(file);
            ZipEntry entry = new ZipEntry(file.getName());
            output.putNextEntry(entry);
            int count;
            while ((count = input.read(data, 0, 1024)) != -1) {
                output.write(data, 0, count);
            }
            input.close();
        }
        output.close();
    }

    /**
     * 功能描述: 压缩目录
     * @author chengdongyi
     * @date 2020/11/4 11:15
     * @param zipFile zip 文件名
     * @param files   文件
     * @return void
     */
    public static void zipdir(String zipFile, String files) throws IOException {
        @Cleanup ZipOutputStream output = new ZipOutputStream(new FileOutputStream(new File(zipFile)));
        File source = new File(files);
        compress(output, source, source.getName());
        output.close();
    }

    /**
     * 功能描述: 递归压缩
     * @author chengdongyi
     * @date 2020/11/4 16:53
     * @param output
     * @param sourceFile 文件
     * @param fileName 文件名
     * @return void
     */
    private static void compress(ZipOutputStream output, File sourceFile, String fileName) throws IOException {
        byte[] buf = new byte[1024];
        if (sourceFile.isFile()) {
            output.putNextEntry(new ZipEntry(fileName));
            int len;
            @Cleanup FileInputStream input = new FileInputStream(sourceFile);
            while ((len = input.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            output.closeEntry();
            input.close();
        } else {
            File[] files = sourceFile.listFiles();
            if(files == null || files.length == 0) {
                output.putNextEntry(new ZipEntry(fileName + File.separator));
                output.closeEntry();
            } else {
                for (File file : files) {
                    compress(output, file, fileName + File.separator + file.getName());
                }
            }
        }
    }

    /**
     * 功能描述: 解压文件
     * @author chengdongyi
     * @date 2020/11/3 16:46
     * @param fileName 压缩文件
     * @return boolean
     */
    public static boolean unzip(String fileName) throws IOException {
        File file = new File(fileName);
        return unzip(fileName, file.getParent());
    }

    /**
     * 功能描述: 解压文件
     * @author chengdongyi
     * @date 2020/11/3 17:00
     * @param fileName 压缩文件
     * @param path     解压后的文件路径
     * @return boolean
     */
    public static boolean unzip(String fileName, String path) throws IOException {

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件《" + file.getName() + "》不存在, 文件路径：" + file.getPath());
            return false;
        }
        System.out.println("压缩文件《" + file.getName() + "》, 文件路径：" + file.getPath());
        System.out.println("解压路径：" + path);
        file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        @Cleanup ZipInputStream input = new ZipInputStream(new FileInputStream(fileName), charset);
        ZipEntry entry = null;
        while ((entry = input.getNextEntry()) != null) {

            String unPath = path + File.separator + entry.getName();
            System.out.println("解压文件：" + entry.getName());
            if (entry.isDirectory()) {
                file = new File(unPath);
                file.mkdirs();
            } else {
                @Cleanup FileOutputStream output = new FileOutputStream(unPath, false);
                for (int b = input.read(); b != -1; b = input.read()) {
                    output.write(b);
                }
            }
            input.closeEntry();
        }
        input.close();
        return true;
    }

}
