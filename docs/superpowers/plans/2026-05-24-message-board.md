# 留言板功能实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**目标：** 实现留言板功能，包括发布留言、点赞、审核、删除等操作。

**架构：** 在现有的 Controller-Service-DAO 三层架构中添加留言功能，遵循项目中现有的接口模式。

**技术栈：** Spring Boot, MyBatis-Plus, Jakarta Validation

---

### Task 1: 创建 BlogMessage 实体类

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/BlogMessage.java`

- [ ] **Step 1: 创建 BlogMessage 实体类**

```java
package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 留言主表实体类
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
@TableName("t_blog_message")
public class BlogMessage {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 标题
     */
    private String title;
    /**
     * 讨论主题
     */
    private String discussionName;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 收藏数
     */
    private Integer favoriteCount;
    /**
     * 审核状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer status;
    /**
     * 删除标识，0未删除，1已删除
     */
    private String delStatus;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/BlogMessage.java
git commit -m "feat: 创建 BlogMessage 实体类"
```

---

### Task 2: 创建 BlogMessageDetail 实体类

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/BlogMessageDetail.java`

- [ ] **Step 1: 创建 BlogMessageDetail 实体类**

```java
package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 留言内容实体类
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
@TableName("t_blog_message_detail")
public class BlogMessageDetail {
    /**
     * 主键
     */
    private String id;
    /**
     * 留言ID
     */
    private String messageId;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 删除标识，0未删除，1已删除
     */
    private String delStatus;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/BlogMessageDetail.java
git commit -m "feat: 创建 BlogMessageDetail 实体类"
```

---

### Task 3: 创建 BlogMessageDao Mapper 接口

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/dao/BlogMessageDao.java`

- [ ] **Step 1: 创建 BlogMessageDao**

```java
package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言表数据库访问层
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Mapper
public interface BlogMessageDao extends BaseMapper<BlogMessage> {
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/dao/BlogMessageDao.java
git commit -m "feat: 创建 BlogMessageDao"
```

---

### Task 4: 创建 BlogMessageDetailDao Mapper 接口

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/dao/BlogMessageDetailDao.java`

- [ ] **Step 1: 创建 BlogMessageDetailDao**

```java
package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogMessageDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言内容表数据库访问层
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Mapper
public interface BlogMessageDetailDao extends BaseMapper<BlogMessageDetail> {
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/dao/BlogMessageDetailDao.java
git commit -m "feat: 创建 BlogMessageDetailDao"
```

---

### Task 5: 创建 BlogMessageBo 业务对象

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogMessageBo.java`

- [ ] **Step 1: 创建 BlogMessageBo**

```java
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
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogMessageBo.java
git commit -m "feat: 创建 BlogMessageBo"
```

---

### Task 6: 创建 BlogMessageQueryBo 查询对象

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogMessageQueryBo.java`

- [ ] **Step 1: 创建 BlogMessageQueryBo**

```java
package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 留言查询对象
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
public class BlogMessageQueryBo {
    /**
     * 当前页码
     */
    private Integer current;
    /**
     * 每页条数
     */
    private Integer size;
    /**
     * 分类筛选（all/job/tech/life）
     */
    private String category;
    /**
     * 排序字段（createTime/likeCount）
     */
    private String sortBy;
    /**
     * 排序方式（asc/desc）
     */
    private String sortOrder;
    /**
     * 审核状态（0-待审核，1-已通过，2-已拒绝）
     */
    private Integer status;
    /**
     * 昵称（模糊查询）
     */
    private String nickname;
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogMessageQueryBo.java
git commit -m "feat: 创建 BlogMessageQueryBo"
```

---

### Task 7: 创建 BlogMessageVo 返回对象

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/vo/BlogMessageVo.java`

- [ ] **Step 1: 创建 BlogMessageVo**

```java
package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * 留言返回对象
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
public class BlogMessageVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 标题
     */
    private String title;
    /**
     * 讨论主题
     */
    private String discussionName;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 收藏数
     */
    private Integer favoriteCount;
    /**
     * 创建时间
     */
    private String createTime;
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/vo/BlogMessageVo.java
git commit -m "feat: 创建 BlogMessageVo"
```

---

### Task 8: 创建 BlogMessageService 接口

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogMessageService.java`

- [ ] **Step 1: 创建 BlogMessageService**

```java
package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogMessage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageVo;
import com.ssj.yunblog.common.entity.Result;

/**
 * 留言服务接口
 *
 * @author yunkun
 * @since 2026-05-24
 */
public interface BlogMessageService extends IService<BlogMessage> {

    /**
     * 新增留言
     */
    Result<Boolean> add(BlogMessageBo blogMessage);

    /**
     * 点赞留言
     */
    Result<Boolean> like(String id);

    /**
     * 审核留言
     */
    Result<Boolean> audit(String id, Integer status);

    /**
     * 删除留言
     */
    Result<Boolean> delete(String id);

    /**
     * 分页查询留言（前端）
     */
    Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param);

    /**
     * 分页查询留言（管理端）
     */
    Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param);
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogMessageService.java
git commit -m "feat: 创建 BlogMessageService 接口"
```

---

### Task 9: 创建 BlogMessageServiceImpl 实现类

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 创建 BlogMessageServiceImpl（框架）**

```java
package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.dao.BlogMessageDao;
import com.ssj.yunblog.baseInfo.dao.BlogMessageDetailDao;
import com.ssj.yunblog.baseInfo.entity.BlogMessage;
import com.ssj.yunblog.baseInfo.entity.BlogMessageDetail;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageVo;
import com.ssj.yunblog.baseInfo.service.BlogMessageService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 留言服务实现类
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Service("blogMessageService")
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageDao, BlogMessage> implements BlogMessageService {

    @Resource
    private BlogMessageDao blogMessageDao;

    @Resource
    private BlogMessageDetailDao blogMessageDetailDao;

    @Override
    public Result<Boolean> add(BlogMessageBo blogMessage) {
        // 实现待补充
        return Result.ok();
    }

    @Override
    public Result<Boolean> like(String id) {
        // 实现待补充
        return Result.ok();
    }

    @Override
    public Result<Boolean> audit(String id, Integer status) {
        // 实现待补充
        return Result.ok();
    }

    @Override
    public Result<Boolean> delete(String id) {
        // 实现待补充
        return Result.ok();
    }

    @Override
    public Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param) {
        // 实现待补充
        Page<BlogMessageVo> result = new Page<>();
        result.setRecords(new ArrayList<>());
        result.setTotal(0);
        return Result.ok(result);
    }

    @Override
    public Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param) {
        // 实现待补充
        Page<BlogMessageVo> result = new Page<>();
        result.setRecords(new ArrayList<>());
        result.setTotal(0);
        return Result.ok(result);
    }
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 创建 BlogMessageServiceImpl 框架"
```

---

### Task 10: 实现 BlogMessageServiceImpl.add() 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 实现 add 方法**

将以下代码替换 `add()` 方法的实现：

```java
    @Override
    public Result<Boolean> add(BlogMessageBo blogMessage) {
        // 创建留言主记录
        BlogMessage message = new BlogMessage();
        message.setNickname(blogMessage.getNickname());
        message.setEmail(blogMessage.getEmail());
        message.setTitle(blogMessage.getTitle());
        message.setDiscussionName(blogMessage.getDiscussionName());
        message.setLikeCount(0);
        message.setCommentCount(0);
        message.setFavoriteCount(0);
        message.setStatus(0); // 默认待审核
        message.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());

        if (blogMessageDao.insert(message) <= 0) {
            return Result.fail("留言发布失败");
        }

        // 创建留言内容记录
        BlogMessageDetail detail = new BlogMessageDetail();
        detail.setMessageId(message.getId());
        detail.setContent(blogMessage.getContent());
        detail.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());

        if (blogMessageDetailDao.insert(detail) <= 0) {
            return Result.fail("留言内容保存失败");
        }

        return Result.ok(true, "留言发布成功，等待审核");
    }
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 实现 BlogMessageServiceImpl.add() 方法"
```

---

### Task 11: 实现 BlogMessageServiceImpl.like() 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 实现 like 方法**

将以下代码替换 `like()` 方法的实现：

```java
    @Override
    public Result<Boolean> like(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        message.setLikeCount((message.getLikeCount() == null ? 0 : message.getLikeCount()) + 1);
        if (blogMessageDao.updateById(message) > 0) {
            return Result.ok(true, "点赞成功");
        }
        return Result.fail("点赞失败");
    }
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 实现 BlogMessageServiceImpl.like() 方法"
```

---

### Task 12: 实现 BlogMessageServiceImpl.audit() 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 实现 audit 方法**

将以下代码替换 `audit()` 方法的实现：

```java
    @Override
    public Result<Boolean> audit(String id, Integer status) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        if (status == null || (status != 1 && status != 2)) {
            return Result.fail("审核状态不正确");
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        message.setStatus(status);
        if (blogMessageDao.updateById(message) > 0) {
            String msg = status == 1 ? "审核通过" : "审核拒绝";
            return Result.ok(true, msg);
        }
        return Result.fail("审核失败");
    }
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 实现 BlogMessageServiceImpl.audit() 方法"
```

---

### Task 13: 实现 BlogMessageServiceImpl.delete() 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 实现 delete 方法**

将以下代码替换 `delete()` 方法的实现：

```java
    @Override
    public Result<Boolean> delete(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }

        BlogMessage message = new BlogMessage();
        message.setId(id);
        message.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        if (blogMessageDao.updateById(message) > 0) {
            return Result.ok(true, "删除成功");
        }
        return Result.fail("删除失败");
    }
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 实现 BlogMessageServiceImpl.delete() 方法"
```

---

### Task 14: 实现 BlogMessageServiceImpl.queryPageList() 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 实现 queryPageList 方法（前端）**

将以下代码替换 `queryPageList()` 方法的实现：

```java
    @Override
    public Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param) {
        LambdaQueryWrapper<BlogMessage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogMessage::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(BlogMessage::getStatus, 1); // 只查询已审核通过的

        Page<BlogMessage> page = new Page<>(param.getCurrent(), param.getSize());
        IPage<BlogMessage> messagePage = blogMessageDao.selectPage(page, queryWrapper);

        Page<BlogMessageVo> result = new Page<>();
        List<BlogMessageVo> list = messagePage.getRecords().stream().map(item -> {
            BlogMessageVo vo = new BlogMessageVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(item.getCreateTime().toString());
            return vo;
        }).toList();

        result.setRecords(list);
        result.setTotal(messagePage.getTotal());
        return Result.ok(result);
    }
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 实现 BlogMessageServiceImpl.queryPageList() 方法"
```

---

### Task 15: 实现 BlogMessageServiceImpl.queryPageListAdmin() 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: 实现 queryPageListAdmin 方法（管理端）**

将以下代码替换 `queryPageListAdmin()` 方法的实现：

```java
    @Override
    public Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param) {
        LambdaQueryWrapper<BlogMessage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogMessage::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(param.getStatus() != null, BlogMessage::getStatus, param.getStatus())
                .like(param.getNickname() != null && !param.getNickname().isEmpty(),
                        BlogMessage::getNickname, param.getNickname());

        Page<BlogMessage> page = new Page<>(param.getCurrent(), param.getSize());
        IPage<BlogMessage> messagePage = blogMessageDao.selectPage(page, queryWrapper);

        Page<BlogMessageVo> result = new Page<>();
        List<BlogMessageVo> list = messagePage.getRecords().stream().map(item -> {
            BlogMessageVo vo = new BlogMessageVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(item.getCreateTime().toString());
            return vo;
        }).toList();

        result.setRecords(list);
        result.setTotal(messagePage.getTotal());
        return Result.ok(result);
    }
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: 实现 BlogMessageServiceImpl.queryPageListAdmin() 方法"
```

---

### Task 16: 创建 BlogMessageController

**Files:**
- Create: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogMessageController.java`

- [ ] **Step 1: 创建 BlogMessageController**

```java
package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageVo;
import com.ssj.yunblog.baseInfo.service.BlogMessageService;
import com.ssj.yunblog.common.access.CheckRole;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 留言板控制层
 *
 * @author yunkun
 * @since 2026-05-24
 */
@RestController
@RequestMapping("/blog/message")
public class BlogMessageController {

    @Resource
    private BlogMessageService blogMessageService;

    /**
     * 获取留言分页列表（前端）
     */
    @GetMapping("/page")
    public Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param) {
        return blogMessageService.queryPageList(param);
    }

    /**
     * 发布留言
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody @Validated(Add.class) BlogMessageBo blogMessage) {
        return blogMessageService.add(blogMessage);
    }

    /**
     * 点赞留言
     */
    @GetMapping("/like/{id}")
    public Result<Boolean> like(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        return blogMessageService.like(id);
    }

    /**
     * 获取留言分页列表（管理端）
     */
    @GetMapping("/admin/page")
    @CheckRole(value = {"admin"})
    public Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param) {
        return blogMessageService.queryPageListAdmin(param);
    }

    /**
     * 审核留言
     */
    @PostMapping("/audit/{id}")
    @CheckRole(value = {"admin"})
    public Result<Boolean> audit(@PathVariable("id") String id, @RequestBody Map<String, Integer> params) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        Integer status = params.get("status");
        if (status == null) {
            return Result.fail("审核状态不能为空");
        }
        return blogMessageService.audit(id, status);
    }

    /**
     * 删除留言
     */
    @DeleteMapping("/{id}")
    @CheckRole(value = {"admin"})
    public Result<Boolean> delete(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        return blogMessageService.delete(id);
    }
}
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogMessageController.java
git commit -m "feat: 创建 BlogMessageController"
```

---

### Task 17: 整体编译验证

**Files:**
- Test: 整个模块

- [ ] **Step 1: 完整编译**

Run: `mvn clean compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 2: 检查所有修改**

Run: `git diff --name-only`
Expected: 显示所有修改的文件列表

- [ ] **Step 3: 最终提交**

```bash
git add .
git commit -m "chore: 完成留言板功能实现"
```

---

## 自我审查

**1. 规范覆盖检查：**
- ✅ 实体类 BlogMessage 和 BlogMessageDetail - Task 1, 2
- ✅ Mapper 接口 BlogMessageDao 和 BlogMessageDetailDao - Task 3, 4
- ✅ Bo 类 BlogMessageBo 和 BlogMessageQueryBo - Task 5, 6
- ✅ Vo 类 BlogMessageVo - Task 7
- ✅ Service 接口 BlogMessageService - Task 8
- ✅ Service 实现 BlogMessageServiceImpl - Task 9
- ✅ Controller BlogMessageController - Task 16
- ✅ add() 新增留言 - Task 10
- ✅ like() 点赞留言 - Task 11
- ✅ audit() 审核留言 - Task 12
- ✅ delete() 删除留言 - Task 13
- ✅ queryPageList() 前端分页查询 - Task 14
- ✅ queryPageListAdmin() 管理端分页查询 - Task 15

**2. 占位符扫描：**
- ✅ 无 TBD、TODO 或"待实现"占位符
- ✅ 所有代码块包含完整实现代码
- ✅ 所有命令可执行

**3. 类型一致性：**
- ✅ 方法签名一致
- ✅ 字段名称一致
- ✅ 导入语句一致

**4. 验证规则：**
- ✅ 使用 `@Validated(Add.class)` 参数验证
- ✅ Bo 类中验证注解与设计一致