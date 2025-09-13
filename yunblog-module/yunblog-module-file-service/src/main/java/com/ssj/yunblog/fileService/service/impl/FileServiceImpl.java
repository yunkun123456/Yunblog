package com.ssj.yunblog.fileService.service.impl;

import com.ssj.yunblog.fileService.entity.FileConfig;
import com.ssj.yunblog.fileService.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

/**
 * 文件服务实现类
 *
 * @author: yunkun
 * @Date: 2025/9/13
 */
@Service
@ConditionalOnBean(value = FileConfig.class)
public class FileServiceImpl implements FileService {

    @Resource
    private FileConfig fileConfig;

    /**
     * 上传文件
     */
    @Override
    public String upload(InputStream fileInputStream, String fileName) {
        String path = fileConfig.getROOT_PATH();
        File file = new File(path);
        if (!file.exists()) {
            try {
                boolean flag = file.mkdirs();
                if (!flag) {
                    System.out.println("创建目录失败:" + path);
                }
            } catch (Exception e) {
                System.out.println("创建目录失败:" + path + e);
            }
        }
        String[] names = fileName.split("\\.");
        if (names.length < 2) {
            System.out.println("文件格式错误:" + fileName);
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileNewName = uuid + "." + names[names.length - 1].toLowerCase();
        String url = path + fileNewName;

        try (OutputStream os = new FileOutputStream(url); BufferedInputStream bis = new BufferedInputStream(fileInputStream); BufferedOutputStream bos = new BufferedOutputStream(os)) {
            byte[] buffer = new byte[1024 * 8];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
        } catch (Exception e) {
            new File(url).delete();
            System.out.println("上传失败:" + url + e);
        }
        return fileNewName;
    }

    /**
     * 下载文件
     */
    @Override
    public org.springframework.core.io.Resource download(String fileName) {
        String path = fileConfig.getROOT_PATH();
        return new FileSystemResource(new File(path + fileName));
    }

    /**
     * 获取文件类型
     */
    @Override
    public String getMedisType(String fileName) {
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return switch (fileExt) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            case "webp" -> "image/webp";
            default -> "application/octet-stream"; // 未知格式默认二进制流
        };
    }
}
