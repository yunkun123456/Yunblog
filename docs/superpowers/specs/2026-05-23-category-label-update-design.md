# 分类和标签更新接口设计文档

**日期：** 2026-05-23

## 概述

为博客分类和标签添加更新接口，允许管理员更新分类和标签的基本信息。

## 功能需求

### 分类信息更新

**接口：** `PUT /blog/category`

**权限：** 仅管理员 (`@CheckRole(value = {"admin"})`)

**可更新字段：**
- `categoryName` - 分类名称
- `categoryLevel` - 分类等级
- `picUrl` - 图片地址
- `parentId` - 父级分类 ID
- `sortNum` - 排序字段

**验证规则：**
- `categoryName`: 必填，最大长度 40
- `categoryLevel`: 必填
- `parentId`: 必填

### 标签信息更新

**接口：** `PUT /blog/label`

**权限：** 仅管理员 (`@CheckRole(value = {"admin"})`)

**可更新字段：**
- `labelName` - 标签名称
- `categoryId` - 所属分类 ID

**验证规则：**
- `labelName`: 必填，最大长度 30
- `categoryId`: 必填

## 架构设计

### 数据流

```
Client Request (PUT /blog/category)
    ↓
Controller (BlogCategoryController)
    ↓
Service Interface (BlogCategoryService)
    ↓
Service Implementation (BlogCategoryServiceImpl)
    ↓
DAO (MyBatis-Plus updateById)
    ↓
Database
```

### 组件说明

#### Controller 层

**BlogCategoryController:**
- 新增 `update()` 方法
- 使用 `@PutMapping` 注解
- 使用 `@CheckRole(value = {"admin"})` 权限控制
- 使用 `@Validated(Update.class)` 参数验证

**BlogLabelController:**
- 新增 `update()` 方法
- 使用 `@PutMapping` 注解
- 使用 `@CheckRole(value = {"admin"})` 权限控制
- 使用 `@Validated(Update.class)` 参数验证

#### Service 层

**BlogCategoryService:**
- 新增 `Result<Boolean> update(BlogCategoryBo blogCategory)` 方法声明

**BlogLabelService:**
- 新增 `Result<Boolean> update(BlogLabelBo blogLabel)` 方法声明

#### ServiceImpl 层

**BlogCategoryServiceImpl:**
- 实现 `update()` 方法
- 将 Bo 转换为 Entity
- 使用 `blogCategoryDao.updateById()` 更新
- 检查更新结果，返回相应的 Result

**BlogLabelServiceImpl:**
- 实现 `update()` 方法
- 将 Bo 转换为 Entity
- 使用 `blogLabelDao.updateById()` 更新
- 检查更新结果，返回相应的 Result

## 数据模型

### 需要修改的 Bo 类

**BlogCategoryBo:**
- 添加 `id` 字段（必填），用于指定要更新的记录

**BlogLabelBo:**
- 添加 `id` 字段（必填），用于指定要更新的记录

## 错误处理

1. **参数验证失败** - 返回 400，包含具体的验证错误信息
2. **权限不足** - 由 `@CheckRole` 拦截，返回 403
3. **更新失败** - 返回 `Result.fail("更新失败！")`

## 约束和假设

1. 更新操作不检查记录是否存在（由 MyBatis-Plus updateById 返回影响行数判断）
2. 不执行额外的业务规则验证（如父子关系检查）
3. 自动填充 `updateBy` 和 `updateTime` 字段（由 MyMetaObjectHandler 处理）

## 测试要点

1. 使用有效数据更新分类，验证返回成功
2. 使用有效数据更新标签，验证返回成功
3. 使用无效参数（如空字符串、超长字符串），验证返回验证错误
4. 使用非管理员账号，验证返回 403