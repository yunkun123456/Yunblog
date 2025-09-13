package com.ssj.yunblog.fileService.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件服务配置类
 *
 * @author: yunkun
 * @Date: 2025/9/13
 */
@Component
@ConfigurationProperties(prefix = "file.service.path")
public class FileConfig {

    private String ROOT_PATH;

    public String getROOT_PATH() {
        return ROOT_PATH;
    }

    public void setROOT_PATH(String ROOT_PATH) {
        this.ROOT_PATH = ROOT_PATH;
    }
}
