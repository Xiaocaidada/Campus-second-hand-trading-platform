package com.campusmarket.controller;

import com.campusmarket.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping("/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        // 1. 校验文件非空
        if (file.isEmpty()) {
            return Result.fail(500,"文件不能为空");
        }

        // 2. 获取项目根目录，拼接 resources 下上传文S件夹绝对路径
        String projectRoot = System.getProperty("user.dir");
        String uploadDir = projectRoot + "/src/main/resources/upload/images/";
        System.out.println("文件创建地点："+uploadDir);
        // 前端浏览器访问的URL前缀
        String baseUrl = "http://localhost:8080/upload/images/";

        // 3. 递归创建多级文件夹
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 4. 截取文件后缀，生成UUID唯一文件名，防止重名覆盖
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;

        // 5. 目标文件对象
        File dest = new File(uploadDir + fileName);

        try {
            // 6. 将接收的文件写入本地resources/upload/images目录
            file.transferTo(dest);
            // 7. 返回可直接访问的图片相对地址
            return Result.ok(baseUrl + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(500,"图片上传失败：" + e.getMessage());
        }
    }
}