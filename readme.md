# YunBlog

基于 Spring Boot 3 的个人博客系统后端

## 技术栈

- **Java 21**
- **Spring Boot 3.2.1**
- **MyBatis-Plus 3.5.5**
- **MySQL 8.0**
- **Redis**
- **Sa-Token 1.44.0** (权限认证)

## 项目结构

```
YunBlog/
├── yunblog-admin/                    # 管理模块（启动入口）
│   └── controller/
│       ├── UserInfoController.java   # 用户管理（注册、登录）
│       ├── RoleController.java       # 角色管理
│       └── PermissionController.java # 权限管理
│
├── yunblog-common/                   # 公共模块
│   └── entity/
│       └── Result.java               # 统一响应封装
│
└── yunblog-module/                   # 业务模块
    ├── yunblog-module-base-info/     # 博客基础信息模块
    │   └── controller/
    │       ├── BlogInfoController.java      # 博客文章管理
    │       ├── BlogCategoryController.java  # 分类管理
    │       ├── BlogLabelController.java     # 标签管理
    │       ├── BlogCommentController.java   # 评论管理
    │       ├── BlogMessageController.java   # 留言管理
    │       ├── BlogRecommendController.java # 推荐管理
    │       └── KnowledgeController.java     # 知识库管理
    │
    ├── yunblog-module-file-service/  # 文件服务模块
    │   └── controller/
    │       └── FileController.java   # 文件上传/下载
    │
    ├── yunblog-module-setting/       # 设置模块
    │   └── controller/
    │       └── BlogSettingsController.java # 博客设置
    │
    └── yunblog-module-search/        # 搜索模块
        └── controller/
            └── SearchController.java # 搜索功能
```

## 主要功能

### 用户模块
- 用户注册、登录
- 个人信息修改
- 角色权限管理 (RBAC)

### 博客模块
- 博客文章 CRUD
- 分类管理
- 标签管理
- 评论管理
- 留言管理
- 点赞功能
- 每日推荐（最新/最热）

### 文件服务
- 文件上传
- 文件下载

### 搜索模块
- 关键词搜索

## 环境配置

项目支持多环境配置：

- `dev` - 开发环境（默认）
- `prod` - 生产环境

### 打包命令

```bash
# 开发环境打包
mvn clean package -Pdev

# 生产环境打包
mvn clean package -Pprod
```

## 开发流程

1. 创建个人分支
2. 编写代码并提交到个人分支
3. 推送分支到远程仓库
4. 创建合并请求

## 部署说明

### 后端部署

```bash
# 打包
mvn clean package -Pprod

# 启动
java -jar yunblog-admin/target/yunblog-admin-0.0.1-SNAPSHOT.jar
```

### 配置文件位置

- 开发环境: `yunblog-admin/src/main/resources/application-dev.yml`
- 生产环境: `yunblog-admin/src/main/resources/application-prod.yml`
