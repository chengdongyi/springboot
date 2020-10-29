package com.example.controller;


import com.alibaba.excel.EasyExcel;
import com.example.excel.data.DemoData;
import com.example.excel.data.OrderData;
import com.example.excel.event.DemoDataListener;
import lombok.Cleanup;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chengdongyi
 * @description: 文件上传下载
 * @date 2020/10/27 16:35
 */
@RestController
@RequestMapping("/server")
public class ResourceController {

    /**
     * 文件上传 upload
     * <p>
     * String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
     * 获取访问文件的链接
     * String filePath= req.getScheme() +"://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + filename;
     * <p>
     * PostMan测试：
     * 1. 选择 Headers
     * key: Content-Type Value: multipart/form-data
     * 2. 选择 Body --> form-data, 选择文件 file
     * key 必须要与 MultipartFile 的值保持一致
     */
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req) {

        File folder = new File("D:\\test\\oms");
        System.out.println("绝对路径： " + folder.getAbsolutePath());
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String filename = uploadFile.getOriginalFilename();
        System.out.println("文件名称：" + filename);
        try {
            Path path = Paths.get("D:\\test\\oms\\" + filename);
            if (Files.exists(path)) {
                Files.delete(path);
            }
            uploadFile.transferTo(new File(folder, filename));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/oms/" + filename;
            return "上传成功 " + filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }

    /**
     * 多文件上传 uploads
     */
    @PostMapping("/uploads")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest req) {

        File folder = new File("D:\\test\\oms");
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        try {
            for (MultipartFile uploadFile : uploadFiles) {
                String filename = uploadFile.getOriginalFilename();
                Path path = Paths.get("D:\\test\\oms\\" + filename);
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                uploadFile.transferTo(new File(folder, filename));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败！";
        }

        return "上传成功！";
    }

    /**
     * 文件下载 download
     */
    @RequestMapping("/download/{fileName}")
    public String download(@PathVariable String fileName, HttpServletResponse response) throws Exception {

        String filePath = "D:\\test\\oms" + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            return "文件《" + file.getName() + "》不存在！";
        }
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        response.setContentType("application/octet-stream");
        @Cleanup BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        @Cleanup OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        // 如果有返回值时, outputStream.close() 会抛出异常
        outputStream.close();
        inputStream.close();
        return "下载成功！";
    }

    /**
     * 文件下载 downloadNet
     */
    @RequestMapping("/downloadNet")
    public String downloadNet(HttpServletResponse response) throws Exception {

        URL url = new URL("https://res.cmicrwx.cn/rs/res3/rwkweb/blbl/20200228/blblBg.png");
        URLConnection conn = url.openConnection();
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("blblBg.png", "UTF-8"));
        response.setContentType("application/octet-stream");
        @Cleanup BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            response.getOutputStream().write(buffer, 0, len);
        }
        inputStream.close();
        return "下载成功！";
    }

    /**
     * Excel 导入 import
     * easyexcel 语雀文档
     * https://www.yuque.com/easyexcel/doc/write
     */
    @RequestMapping("/import")
    public String excelImport(MultipartFile uploadFile) throws Exception {
        EasyExcel.read(uploadFile.getInputStream(), DemoData.class, new DemoDataListener()).sheet().doRead();
        return "success";
    }

    public void read() {
        String fileName = "D:\\test\\oms\\" + "test" + ".xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
        // EasyExcel.read(fileName, DemoData.class, new DemoDataListener(demoDao)).sheet().doRead();
    }

    public void write() {
        String fileName = "D:\\test\\oms\\" + "test" + ".xlsx";
        EasyExcel.write(fileName, OrderData.class).sheet().doWrite(data());
    }

    private static List<OrderData> data() {
        List<OrderData> list = new ArrayList<>();
        for (int i = 1; i < 23; i++) {
            OrderData data = new OrderData();
            String num = String.format("%02d", i);
            data.setOrderId("No.000000" + num);
            data.setMobile("136846900" + num);
            data.setStatus("1");
            data.setOrderTime(new Date());
            list.add(data);
        }
        return list;
    }

    /**
     * 获取图片尺寸
     */
    public void getImgSize() throws Exception {
        File picture = new File("D:\\test\\oms\\save_temp\\blblBg.png");
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
        System.out.println(String.format("%.1f", picture.length() / 1024.0));
        System.out.println(sourceImg.getWidth());
        System.out.println(sourceImg.getHeight());
    }

}
