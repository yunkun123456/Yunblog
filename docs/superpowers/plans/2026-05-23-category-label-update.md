# 分类和标签更新接口实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**目标：** 为博客分类和标签添加更新接口，允许管理员更新分类和标签的基本信息。

**架构：** 在现有的 Controller-Service-DAO 三层架构中添加更新功能，遵循项目中现有的接口模式。

**技术栈：** Spring Boot, MyBatis-Plus, Jakarta Validation

---

### Task 1: 为 BlogCategoryBo 添加 id 字段

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogCategoryBo.java`

- [ ] **Step 1: 添加 id 字段（在类中添加）**

```java
/**
 * 主键id
 */
@NotBlank(message = "id不能为空！", groups = {Update.class})
private String id;
```

在类的字段区域添加该字段（在其他字段之前或之后）。

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogCategoryBo.java
git commit -m "feat: BlogCategoryBo 添加 id 字段"
```

---

### Task 2: 为 BlogLabelBo 添加 id 字段

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogLabelBo.java`

- [ ] **Step 1: 添加 id 字段（在类中添加）**

```java
/**
 * 主键id
 */
@NotBlank(message = "id不能为空！", groups = {Update.class})
private String id;
```

在类的字段区域添加该字段（在其他字段之前或之后）。

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/entity/bo/BlogLabelBo.java
git commit -m "feat: BlogLabelBo 添加 id 字段"
```

---

### Task 3: BlogCategoryService 添加 update 方法声明

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogCategoryService.java`

- [ ] **Step 1: 添加 update 方法声明**

在接口中添加以下方法（在 `queryPageList` 方法之后）：

```java
/**
 * 更新分类信息
 */
Result<Boolean> update(BlogCategoryBo blogCategory);
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogCategoryService.java
git commit -m "feat: BlogCategoryService 添加 update 方法声明"
```

---

### Task 4: BlogLabelService 添加 update 方法声明

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogLabelService.java`

- [ ] **Step 1: 添加 update 方法声明**

在接口中添加以下方法（在 `queryPageList` 方法之后）：

```java
/**
 * 更新标签信息
 */
Result<Boolean> update(BlogLabelBo blogLabel);
```

- [ ] **Step 2: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/BlogLabelService.java
git commit -m "feat: BlogLabelService 添加 update 方法声明"
```

---

### Task 5: BlogCategoryServiceImpl 实现 update 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogCategoryServiceImpl.java`

- [ ] **Step 1: 添加 import 语句**

确保文件顶部有以下 import：
```java
import com.ssj.yunblog.common.api.Update;
```

如果没有，添加到现有的 import 区域。

- [ ] **Step 2: 实现 update 方法**

在类的末尾、`buildCategoryTree` 方法之后添加：

```java
/**
 * 更新分类信息
 */
@Override
public Result<Boolean> update(BlogCategoryBo blogCategory) {
    BlogCategory category = new BlogCategory();
    BeanUtils.copyProperties(blogCategory, category);
    if (blogCategoryDao.updateById(category) > 0) {
        return Result.ok();
    }
    return Result.fail("更新分类信息失败");
}
```

- [ ] **Step 3: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogCategoryServiceImpl.java
git commit -m "feat: BlogCategoryServiceImpl 实现 update 方法"
```

---

### Task 6: BlogLabelServiceImpl 实现 update 方法

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogLabelServiceImpl.java`

- [ ] **Step 1: 添加 import 语句**

确保文件顶部有以下 import：
```java
import com.ssj.yunblog.common.api.Update;
```

如果没有，添加到现有的 import 区域。

- [ ] **Step 2: 实现 update 方法**

在类的末尾、`queryPageList` 方法之后添加：

```java
/**
 * 更新标签信息
 */
@Override
public Result<Boolean> update(BlogLabelBo blogLabel) {
    BlogLabel label = new BlogLabel();
    BeanUtils.copyProperties(blogLabel, label);
    if (blogLabelDao.updateById(label) > 0) {
        return Result.ok();
    }
    return Result.fail("更新标签信息失败");
}
```

- [ ] **Step 3: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/service/impl/BlogLabelServiceImpl.java
git commit -m "feat: BlogLabelServiceImpl 实现 update 方法"
```

---

### Task 7: BlogCategoryController 添加更新接口

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogCategoryController.java`

- [ ] **Step 1: 添加 Update 导入**

在文件顶部的 import 区域添加：
```java
import com.ssj.yunblog.common.api.Update;
```

- [ ] **Step 2: 添加更新接口方法**

在 `delete` 方法之后、类结束前添加：

```java
/**
 * 更新分类信息
 */
@CheckRole(value = {"admin"})
@PutMapping
public Result<Boolean> update(@RequestBody @Validated(Update.class) BlogCategoryBo blogCategory) {
    return blogCategoryService.update(blogCategory);
}
```

- [ ] **Step 3: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogCategoryController.java
git commit -m "feat: BlogCategoryController 添加更新接口"
```

---

### Task 8: BlogLabelController 添加更新接口

**Files:**
- Modify: `yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogLabelController.java`

- [ ] **Step 1: 添加 Update 导入**

在文件顶部的 import 区域添加：
```java
import com.ssj.yunblog.common.api.Update;
```

- [ ] **Step 2: 添加更新接口方法**

在 `queryLabelListByCategoryId` 方法之后、类结束前添加：

```java
/**
 * 更新标签信息
 */
@CheckRole(value = {"admin"})
@PutMapping
public Result<Boolean> update(@RequestBody @Validated(Update.class) BlogLabelBo blogLabel) {
    return blogLabelService.update(blogLabel);
}
```

- [ ] **Step 3: 验证编译**

Run: `mvn compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add yunblog-module/yunblog-module-base-info/src/main/java/com/ssj/yunblog/baseInfo/controller/BlogLabelController.java
git commit -m "feat: BlogLabelController 添加更新接口"
```

---

### Task 9: 整体编译和测试

**Files:**
- Test: 整个模块

- [ ] **Step 1: 完整编译**

Run: `mvn clean compile -pl yunblog-module/yunblog-module-base-info`
Expected: BUILD SUCCESS

- [ ] **Step 2: 检查所有修改**

Run: `git diff --name-only`
Expected: 显示所有修改的文件列表

- [ ] **Step 3: 最终提交（如果需要）**

如果有遗漏的修改，执行：
```bash
git add .
git commit -m "chore: 完成分类和标签更新接口实现"
```

---

## 自我审查

**1. 规范覆盖检查：**
- ✅ 分类更新接口 `PUT /blog/category` - Task 7
- ✅ 标签更新接口 `PUT /blog/label` - Task 8
- ✅ 权限控制 `@CheckRole(value = {"admin"})` - Task 7, 8
- ✅ 参数验证 `@Validated(Update.class)` - Task 7, 8
- ✅ 可更新字段 - 通过 BeanUtils.copyProperties 实现
- ✅ BlogCategoryService.update 方法 - Task 3
- ✅ BlogLabelService.update 方法 - Task 4
- ✅ BlogCategoryServiceImpl.update 实现 - Task 5
- ✅ BlogLabelServiceImpl.update 实现 - Task 6
- ✅ BlogCategoryBo 添加 id 字段 - Task 1
- ✅ BlogLabelBo 添加 id 字段 - Task 2

**2. 占位符扫描：**
- ✅ 无 TBD、TODO 或"待实现"占位符
- ✅ 所有代码块包含完整实现代码
- ✅ 所有命令可执行

**3. 类型一致性：**
- ✅ 方法签名一致：`Result<Boolean> update(Bo bo)`
- ✅ 字段名称一致：`categoryName`, `labelName`, `categoryId` 等
- ✅ 导入语句一致：`com.ssj.yunblog.common.api.Update`

**4. 验证规则：**
- ✅ 使用 `@Validated(Update.class)` 参数验证
- ✅ Bo 类中 `@NotBlank` 分组验证与设计一致