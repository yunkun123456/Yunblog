# 留言板功能设计文档

**日期：** 2026-05-24

## 概述

为博客系统添加留言板功能，允许用户发布留言、点赞，管理员可审核和删除留言。

## 功能需求

### 前端接口

#### 1. 获取留言分页列表

**接口：** `GET /blog/message/page`

**请求参数：**
- current (必填) - 当前页码
- size (必填) - 每页条数
- category (可选) - 分类筛选（all/job/tech/life）
- sortBy (可选) - 排序字段（createTime/likeCount）
- sortOrder (可选) - 排序方式（asc/desc）

**响应：** 只显示已审核通过（status=1）的留言

#### 2. 发布留言

**接口：** `POST /blog/message`

**请求参数：**
- discussionName (可选) - 讨论主题
- title (可选) - 留言标题
- nickname (必填) - 昵称
- email (可选) - 邮箱地址
- content (必填) - 留言内容

**逻辑：** 新留言默认 status=0（待审核）

#### 3. 点赞留言

**接口：** `GET /blog/message/like/{id}`

**路径参数：**
- id (必填) - 留言ID

**逻辑：** 直接更新 likeCount

### 管理端接口

#### 4. 获取留言分页列表（管理端）

**接口：** `GET /blog/message/page`

**请求参数：**
- current (必填) - 当前页码
- size (必填) - 每页条数
- status (可选) - 审核状态（0-待审核，1-已通过，2-已拒绝）
- nickname (可选) - 昵称（模糊查询）

#### 5. 审核留言

**接口：** `POST /blog/message/audit/{id}`

**路径参数：**
- id (必填) - 留言ID

**请求参数（Body）：**
- status (必填) - 审核状态（1-通过，2-拒绝）

#### 6. 删除留言

**接口：** `DELETE /blog/message/{id}`

**路径参数：**
- id (必填) - 留言ID

**逻辑：** 软删除（设置 del_status=1）

## 数据库设计

### t_blog_message（留言主表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(32) | 主键 |
| user_id | varchar(32) | 用户ID |
| nickname | varchar(50) | 昵称 |
| avatar | varchar(255) | 头像 |
| email | varchar(100) | 邮箱 |
| title | varchar(200) | 标题 |
| discussion_name | varchar(100) | 讨论主题 |
| like_count | int(11) | 点赞数 |
| comment_count | int(11) | 评论数 |
| favorite_count | int(11) | 收藏数 |
| status | int(11) | 审核状态：0-待审核，1-已通过，2-已拒绝 |
| del_status | char(1) | 删除标识：0-未删除，1-已删除 |
| create_by | varchar(32) | 创建人 |
| create_time | datetime | 创建时间 |
| update_by | varchar(32) | 更新人 |
| update_time | datetime | 更新时间 |

### t_blog_message_detail（留言内容表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(32) | 主键 |
| message_id | varchar(32) | 留言ID |
| content | text | 留言内容 |
| del_status | char(1) | 删除标识：0-未删除，1-已删除 |
| create_by | varchar(32) | 创建人 |
| create_time | datetime | 创建时间 |
| update_by | varchar(32) | 更新人 |
| update_time | datetime | 更新时间 |

## 架构设计

### 数据流

```
Client Request
    ↓
Controller (BlogMessageController)
    ↓
Service Interface (BlogMessageService)
    ↓
Service Implementation (BlogMessageServiceImpl)
    ↓
DAO (MyBatis-Plus)
    ↓
Database (t_blog_message + t_blog_message_detail)
```

### 组件说明

#### Entity 层
- **BlogMessage** - 留言主表实体
- **BlogMessageDetail** - 留言内容实体
- **BlogComment** - 评论实体（预留）

#### DAO 层
- **BlogMessageDao** - 留言主表 Mapper
- **BlogMessageDetailDao** - 留言内容 Mapper

#### Bo 层
- **BlogMessageBo** - 留言业务对象（新增/更新）
- **BlogMessageQueryBo** - 留言查询对象

#### Vo 层
- **BlogMessageVo** - 留言返回对象（包含详情）

#### Service 层
- **BlogMessageService** - 留言服务接口
- add() - 新增留言
- like() - 点赞留言
- audit() - 审核留言
- delete() - 删除留言
- queryPageList() - 分页查询（前端）
- queryPageListAdmin() - 分页查询（管理端）

#### Controller 层
- **BlogMessageController** - 留言控制器

## 约束和假设

1. 留言需要审核才能在前端显示
2. 点赞直接更新数据库，不使用 Redis
3. 评论功能后续单独开发
4. 软删除机制（del_status）
5. 自动填充 create_time、update_time 等字段
6. 审核逻辑后续完善

## 测试要点

1. 发布留言，验证状态为待审核
2. 审核通过后，前端可以查询到
3. 点赞功能正常工作
4. 管理端可以按状态筛选
5. 软删除后前端查询不到