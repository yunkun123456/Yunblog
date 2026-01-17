package com.ssj.yunblog.baseInfo.entity.bo;

import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * (KnowledgeDetail)实体类
 *
 * @author yunkun
 * @since 2026-01-17 15:18:34
 */
@Data
public class KnowledgeDetailBo {
    /**
     * 主键
     */
    private String id;
    /**
     * 知识库信息关联id
     */
    @NotBlank(message = "知识库信息不能为空！", groups = {Add.class, Update.class})
    private String knowledgeInfoId;
    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空！", groups = {Add.class, Update.class})
    private String content;
    /**
     * 图片地址
     */
    private String picUrl;
}

