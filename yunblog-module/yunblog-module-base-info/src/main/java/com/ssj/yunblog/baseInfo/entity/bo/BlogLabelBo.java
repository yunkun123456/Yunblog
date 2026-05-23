package com.ssj.yunblog.baseInfo.entity.bo;

import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 博客标签实体类 bo
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
@Data
public class BlogLabelBo {
    /**
     * 主键id
     */
    @NotBlank(message = "id不能为空！", groups = {Update.class})
    private String id;
    /**
     * 标签名称
     */
    @NotBlank(message = "博客标签名称不能为空！", groups = {Add.class, Update.class})
    @Size(max = 30, message = "博客标签名称长度不能超过30！")
    private String labelName;
    /**
     * 分类id
     */
    @NotBlank(message = "博客分类id不能为空！")
    private String categoryId;
}

