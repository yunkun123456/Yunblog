package com.ssj.yunblog.baseInfo.entity.bo;

import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 博客信息 bo
 *
 * @author: yunkun
 * @Date: 2025/9/14
 */
@Data
public class BlogInfoBo {

    /**
     * 博客信息id
     */
    @NotBlank(message = "博客id不能为空！", groups = {Update.class})
    private String id;

    /**
     * 标题
     */
    @NotBlank(message = "博客标题不能为空！", groups = {Add.class, Update.class})
    @Size(max = 200, message = "博客标题长度不能超过200！", groups = {Add.class, Update.class})
    private String title;
    /**
     * 文章介绍
     */
    @NotBlank(message = "博客介绍不能为空！", groups = {Add.class, Update.class})
    @Size(max = 200, message = "博客介绍长度不能超过200！", groups = {Add.class, Update.class})
    private String introduction;
    /**
     * 类别id
     */
    @NotBlank(message = "博客类别不能为空！", groups = {Add.class, Update.class})
    private String categoryId;
    /**
     * 标签id
     */
    @NotBlank(message = "博客标签不能为空！", groups = {Add.class, Update.class})
    private String labelId;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 博客内容
     */
    @NotBlank(message = "博客内容不能为空！", groups = {Add.class, Update.class})
    @Size(max = 2000, message = "博客内容长度不能超过2000！", groups = {Add.class, Update.class})
    private String content;
    /**
     * 图片地址
     */
    private String picUrl;
}
