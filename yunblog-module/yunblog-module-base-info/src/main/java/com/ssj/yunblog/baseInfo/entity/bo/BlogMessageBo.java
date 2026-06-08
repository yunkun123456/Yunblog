package com.ssj.yunblog.baseInfo.entity.bo;

import com.ssj.yunblog.common.api.Add;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 留言业务对象
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
public class BlogMessageBo {
    /**
     * 讨论主题
     */
    @Size(max = 100, message = "讨论主题长度不能超过100！", groups = {Add.class})
    private String discussionName;
    /**
     * 留言标题
     */
    @Size(max = 200, message = "留言标题长度不能超过200！", groups = {Add.class})
    private String title;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空！", groups = {Add.class})
    @Size(max = 50, message = "昵称长度不能超过50！", groups = {Add.class})
    private String nickname;
    /**
     * 邮箱地址
     */
    @Size(max = 100, message = "邮箱地址长度不能超过100！", groups = {Add.class})
    private String email;
    /**
     * 留言内容
     */
    @NotBlank(message = "留言内容不能为空！", groups = {Add.class})
    private String content;
}