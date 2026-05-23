package com.ssj.yunblog.baseInfo.entity.bo;

import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 实体类 Bo
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
@Data
public class BlogCategoryBo {
    /**
     * 主键id
     */
    @NotBlank(message = "id不能为空！", groups = {Update.class})
    private String id;
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空！", groups = {Add.class, Update.class})
    @Size(max = 40, message = "分类名称长度不能超过40！", groups = {Add.class, Update.class})
    private String categoryName;
    /**
     * 分类级别
     */
    @NotNull(message = "分类级别不能为空")
    private Integer categoryLevel;
    /**
     * 排序字段
     */
    private Integer sortNum;
    /**
     * 图像地址，后续可能会拓展分类icon
     */
    private String picUrl;
    /**
     * 父级id
     */
    @NotBlank(message = "父级id不能为空！", groups = {Add.class, Update.class})
    private String parentId;

}

