package com.ssj.yunblog.fileService.api;

import com.ssj.yunblog.fileService.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 文件服务api
 *
 * @author: yunkun
 * @Date: 2025/9/13
 */
@Component
public class FileServiceApi {

    @Resource
    private FileService fileService;

    /**
     * 上传文件
     *
     * @param fileInputStream 文件输入流
     * @param fileName        文件原始名称
     * @return UUID 文件唯一标识
     */
    public String upload(InputStream fileInputStream, String fileName) {
        return fileService.upload(fileInputStream, fileName);
    }
}
