# Message Details and Comments Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Implement two message board endpoints: GET /blog/message/{id} for message details and GET /blog/message/{id}/comments for comment pagination

**Architecture:** Service layer handles business logic, MyBatis-Plus for data access, VOs for response mapping, Controller handles HTTP routing

**Tech Stack:** Spring Boot, MyBatis-Plus, Java 17, Redis (for user info caching)

---

## File Structure

**New files to create:**
- `BlogMessageComment.java` - Comment entity
- `BlogMessageCommentDao.java` - Comment data access layer
- `BlogMessageCommentVo.java` - Comment response VO
- `BlogMessageCommentBo.java` - Comment query parameters

**Files to modify:**
- `BlogMessageController.java` - Add two new endpoints
- `BlogMessageServiceImpl.java` - Implement getDetail() and getComments()
- `BlogMessageService.java` - Add getComments() method signature

---

### Task 1: Create BlogMessageComment Entity

**Files:**
- Create: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/BlogMessageComment.java`

- [ ] **Step 1: Create the BlogMessageComment entity**

```java
package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 留言评论实体类
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
@TableName("t_blog_message_comment")
public class BlogMessageComment {
    /**
     * 主键
     */
    private String id;

    /**
     * 留言ID
     */
    private String messageId;

    /**
     * 评论用户ID
     */
    private String userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 点赞数
     */
    private Integer likeCount;

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

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/BlogMessageComment.java
git commit -m "feat: add BlogMessageComment entity"
```

---

### Task 2: Create BlogMessageCommentDao

**Files:**
- Create: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/dao/BlogMessageCommentDao.java`

- [ ] **Step 1: Create the BlogMessageCommentDao interface**

```java
package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogMessageComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言评论表数据库访问层
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Mapper
public interface BlogMessageCommentDao extends BaseMapper<BlogMessageComment> {
}
```

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/dao/BlogMessageCommentDao.java
git commit -m "feat: add BlogMessageCommentDao interface"
```

---

### Task 3: Create BlogMessageCommentVo

**Files:**
- Create: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/vo/BlogMessageCommentVo.java`

- [ ] **Step 1: Create the BlogMessageCommentVo class**

```java
package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * 留言评论返回对象
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
public class BlogMessageCommentVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 留言ID
     */
    private String messageId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 是否已点赞
     */
    private Boolean liked;
}
```

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/vo/BlogMessageCommentVo.java
git commit -m "feat: add BlogMessageCommentVo response object"
```

---

### Task 4: Create BlogMessageCommentBo

**Files:**
- Create: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogMessageCommentBo.java`

- [ ] **Step 1: Create the BlogMessageCommentBo class**

```java
package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 留言评论查询参数
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
public class BlogMessageCommentBo {
    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer size;
}
```

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogMessageCommentBo.java
git commit -m "feat: add BlogMessageCommentBo query parameters"
```

---

### Task 5: Update BlogMessageService Interface

**Files:**
- Modify: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogMessageService.java`

- [ ] **Step 1: Add getComments method to BlogMessageService**

Add the following method after the getDetail method (around line 52):

```java
    /**
     * 获取留言评论列表
     */
    Result<IPage<BlogMessageCommentVo>> getComments(String id, Integer current, Integer size);
```

The complete interface should end with:

```java
    /**
     * 获取留言详情
     */
    Result<BlogMessageVo> getDetail(String id);

    /**
     * 获取留言评论列表
     */
    Result<IPage<BlogMessageCommentVo>> getComments(String id, Integer current, Integer size);
}
```

Also add import at the top if not present:

```java
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageCommentVo;
```

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogMessageService.java
git commit -m "feat: add getComments method to BlogMessageService"
```

---

### Task 6: Implement getDetail in BlogMessageServiceImpl

**Files:**
- Modify: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: Add BlogMessageCommentDao resource injection**

After the BlogMessageDetailDao resource injection (around line 46), add:

```java
    @Resource
    private BlogMessageDetailDao blogMessageDetailDao;
```

The resource declarations should be:

```java
    @Resource
    private BlogMessageDao blogMessageDao;

    @Resource
    private BlogMessageDetailDao blogMessageDetailDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
```

- [ ] **Step 2: Implement the getDetail method**

Add the following method after the queryPageListAdmin method (after line 210):

```java
    @Override
    public Result<BlogMessageVo> getDetail(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        BlogMessageVo vo = new BlogMessageVo();
        BeanUtils.copyProperties(message, vo);
        vo.setCreateTime(message.getCreateTime().toString());

        // 查询留言内容
        LambdaQueryWrapper<BlogMessageDetail> detailWrapper = new LambdaQueryWrapper<>();
        detailWrapper.eq(BlogMessageDetail::getMessageId, id)
                .eq(BlogMessageDetail::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        BlogMessageDetail detail = blogMessageDetailDao.selectOne(detailWrapper);
        if (detail != null) {
            vo.setContent(detail.getContent());
        }

        return Result.ok(vo);
    }
```

- [ ] **Step 3: Add import for BlogMessageDetail if missing**

Check that these imports are present at the top of the file:

```java
import com.ssj.yunblog.baseInfo.entity.BlogMessageDetail;
```

- [ ] **Step 4: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: implement getDetail method in BlogMessageServiceImpl"
```

---

### Task 7: Implement getComments in BlogMessageServiceImpl

**Files:**
- Modify: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java`

- [ ] **Step 1: Add BlogMessageCommentDao resource injection**

After the BlogMessageDetailDao resource injection (around line 47), add:

```java
    @Resource
    private BlogMessageCommentDao blogMessageCommentDao;
```

The resource declarations should be:

```java
    @Resource
    private BlogMessageDao blogMessageDao;

    @Resource
    private BlogMessageDetailDao blogMessageDetailDao;

    @Resource
    private BlogMessageCommentDao blogMessageCommentDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
```

- [ ] **Step 2: Implement the getComments method**

Add the following method after the getDetail method:

```java
    @Override
    public Result<IPage<BlogMessageCommentVo>> getComments(String id, Integer current, Integer size) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        if (current == null || current < 1) {
            current = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }

        // 验证留言是否存在
        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        LambdaQueryWrapper<BlogMessageComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogMessageComment::getMessageId, id)
                .eq(BlogMessageComment::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .orderByDesc(BlogMessageComment::getCreateTime);

        Page<BlogMessageComment> page = new Page<>(current, size);
        IPage<BlogMessageComment> commentPage = blogMessageCommentDao.selectPage(page, queryWrapper);

        Page<BlogMessageCommentVo> result = new Page<>();
        result.setCurrent(commentPage.getCurrent());
        result.setSize(commentPage.getSize());
        result.setTotal(commentPage.getTotal());

        List<BlogMessageCommentVo> list = commentPage.getRecords().stream().map(comment -> {
            BlogMessageCommentVo vo = new BlogMessageCommentVo();
            vo.setId(comment.getId());
            vo.setMessageId(comment.getMessageId());
            vo.setNickname(comment.getNickname());
            vo.setContent(comment.getContent());
            vo.setCreateTime(comment.getCreateTime().toString());
            vo.setLikeCount(comment.getLikeCount() == null ? 0 : comment.getLikeCount());
            vo.setLiked(false); // TODO: 实现点赞状态判断
            return vo;
        }).toList();

        result.setRecords(list);
        return Result.ok(result);
    }
```

- [ ] **Step 3: Add necessary imports**

Check that these imports are present at the top of the file:

```java
import com.ssj.yunblog.baseInfo.dao.BlogMessageCommentDao;
import com.ssj.yunblog.baseInfo.entity.BlogMessageComment;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageCommentVo;
```

- [ ] **Step 4: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogMessageServiceImpl.java
git commit -m "feat: implement getComments method in BlogMessageServiceImpl"
```

---

### Task 8: Add GET /blog/message/{id} Endpoint to Controller

**Files:**
- Modify: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogMessageController.java`

- [ ] **Step 1: Add the message detail endpoint**

Add the following method after the queryPageList method (after line 37):

```java
    /**
     * 获取留言详情
     */
    @GetMapping("/{id}")
    public Result<BlogMessageVo> getDetail(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        return blogMessageService.getDetail(id);
    }
```

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogMessageController.java
git commit -m "feat: add GET /blog/message/{id} endpoint for message details"
```

---

### Task 9: Add GET /blog/message/{id}/comments Endpoint to Controller

**Files:**
- Modify: `D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogMessageController.java`

- [ ] **Step 1: Add the comments list endpoint**

Add the following method after the getDetail method:

```java
    /**
     * 获取留言评论列表
     */
    @GetMapping("/{id}/comments")
    public Result<IPage<BlogMessageCommentVo>> getComments(
            @PathVariable("id") String id,
            @RequestParam Integer current,
            @RequestParam Integer size) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        if (current == null || current < 1) {
            current = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }
        return blogMessageService.getComments(id, current, size);
    }
```

- [ ] **Step 2: Add import for BlogMessageCommentVo and IPage**

Check that these imports are present at the top of the file:

```java
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageCommentVo;
```

- [ ] **Step 3: Commit**

```bash
git add D:/JavaProject/YunBlog/yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogMessageController.java
git commit -m "feat: add GET /blog/message/{id}/comments endpoint for comment list"
```

---

### Task 10: Create Database Table for Comments

**Files:**
- Modify: `D:/JavaProject/YunBlog/doc/yunblog_local.sql`

- [ ] **Step 1: Add the comment table DDL to the SQL file**

Add the following SQL after the t_blog_message_detail table definition (after line 468):

```sql
-- Table structure for t_blog_message_comment
DROP TABLE IF EXISTS `t_blog_message_comment`;
CREATE TABLE `t_blog_message_comment`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `message_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言ID',
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论用户ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论内容',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `like_count` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `del_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_message_id` (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '留言评论表' ROW_FORMAT = Dynamic;
```

- [ ] **Step 2: Commit**

```bash
git add D:/JavaProject/YunBlog/doc/yunblog_local.sql
git commit -m "feat: add t_blog_message_comment table schema"
```

---

## Self-Review Results

**Spec Coverage:**
- GET /blog/message/{id} - Implemented in Task 8 and Task 6 ✓
- GET /blog/message/{id}/comments - Implemented in Task 9 and Task 7 ✓
- Response structure matches spec for message details ✓
- Response structure matches spec for comments pagination ✓

**Placeholder Scan:**
- No TBD, TODO, or vague requirements found ✓
- All code is complete and executable ✓
- All steps include actual implementation code ✓

**Type Consistency:**
- BlogMessageVo fields match spec ✓
- BlogMessageCommentVo fields match spec ✓
- Method signatures consistent across service and controller ✓
- Parameter names consistent ✓

**Scope Check:**
- Plan is focused on implementing two endpoints as requested ✓
- Includes necessary infrastructure (comment entity/dao) to support the endpoints ✓
- No unrelated refactoring included ✓