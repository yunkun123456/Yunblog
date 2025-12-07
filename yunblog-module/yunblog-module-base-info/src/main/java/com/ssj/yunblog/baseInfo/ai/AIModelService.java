package com.ssj.yunblog.baseInfo.ai;

/**
 * 大模型相关接口
 * @author: yunkun
 * @Date: 2025/12/7
 */
public interface AIModelService {

    /**
     * 文生图
     */
    void generatePic(String content,String fileName) throws Exception;
}
