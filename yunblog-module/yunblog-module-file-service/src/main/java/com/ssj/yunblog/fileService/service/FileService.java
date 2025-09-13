package com.ssj.yunblog.fileService.service;

import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * 文件服务
 *
 * @author: yunkun
 * @Date: 2025/9/13
 */
public interface FileService {

    /**
     * 上传文件
     */
    String upload(InputStream fileInputStream, String fileName);

    /**
     * 文件下载
     */
    Resource download(String fileName);

    /**
     * 获取文件类型
     */
    String getMedisType(String fileName);
}
