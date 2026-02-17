/*
 Navicat Premium Data Transfer

 Source Server         : 抖音服务器
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : 14.103.150.52:3306
 Source Schema         : yunblog_local

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 03/01/2026 20:03:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog_category
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_category`;
CREATE TABLE `t_blog_category`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `category_name` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `category_level` int NULL DEFAULT NULL COMMENT '分类级别',
  `pic_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图像地址，后续可能会拓展分类icon',
  `parent_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `sort_num` int NULL DEFAULT NULL COMMENT '排序字段',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_category
-- ----------------------------
INSERT INTO `t_blog_category` VALUES ('1', 'JavaScript', 2, NULL, '1992488112189730818', 8, '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_blog_category` VALUES ('1992483507959623682', '后端', 1, '', '0', 0, '0', '1959249411166965761', '2025-11-23 14:41:02', '1959249411166965761', '2025-11-23 14:41:02');
INSERT INTO `t_blog_category` VALUES ('1992488112189730818', '前端', 1, '', '0', 1, '0', '1959249411166965761', '2025-11-23 14:59:19', '1959249411166965761', '2025-11-23 14:59:19');
INSERT INTO `t_blog_category` VALUES ('1992488156515135489', '测试', 1, '', '0', 2, '0', '1959249411166965761', '2025-11-23 14:59:30', '1959249411166965761', '2025-11-23 14:59:30');
INSERT INTO `t_blog_category` VALUES ('1992488219467444225', '运维', 1, '', '0', 3, '0', '1959249411166965761', '2025-11-23 14:59:45', '1959249411166965761', '2025-11-23 14:59:45');
INSERT INTO `t_blog_category` VALUES ('1992488568651640833', '框架', 2, '', '1992483507959623682', 0, '0', '1959249411166965761', '2025-11-23 15:01:08', '1959249411166965761', '2025-11-23 15:01:08');
INSERT INTO `t_blog_category` VALUES ('1992489130247974914', '消息队列', 2, '', '1992483507959623682', 1, '0', '1959249411166965761', '2025-11-23 15:03:22', '1959249411166965761', '2025-11-23 15:03:22');
INSERT INTO `t_blog_category` VALUES ('1992489159654240258', '缓存', 2, '', '1992483507959623682', 2, '0', '1959249411166965761', '2025-11-23 15:03:29', '1959249411166965761', '2025-11-23 15:03:29');
INSERT INTO `t_blog_category` VALUES ('1992489461782540289', '数据库', 2, '', '1992483507959623682', 3, '0', '1959249411166965761', '2025-11-23 15:04:41', '1959249411166965761', '2025-11-23 15:04:41');
INSERT INTO `t_blog_category` VALUES ('1992489809792331778', '搜索引擎', 2, '', '1992483507959623682', 4, '0', '1959249411166965761', '2025-11-23 15:06:04', '1959249411166965761', '2025-11-23 15:06:04');
INSERT INTO `t_blog_category` VALUES ('1992490036519628801', '编程语言', 2, '', '1992483507959623682', 5, '0', '1959249411166965761', '2025-11-23 15:06:58', '1959249411166965761', '2025-11-23 15:06:58');
INSERT INTO `t_blog_category` VALUES ('1992490935300255745', '分布式', 2, '', '1992483507959623682', 6, '0', '1959249411166965761', '2025-11-23 15:10:32', '1959249411166965761', '2025-11-23 15:10:32');
INSERT INTO `t_blog_category` VALUES ('1992495103356301314', '性能优化', 2, '', '1992483507959623682', 7, '0', '1959249411166965761', '2025-11-23 15:27:06', '1959249411166965761', '2025-11-23 15:27:06');

-- ----------------------------
-- Table structure for t_blog_info
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_info`;
CREATE TABLE `t_blog_info`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `introduction` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文章介绍',
  `category_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类别id',
  `label_id` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标签id',
  `like_num` int NULL DEFAULT NULL COMMENT '点赞数',
  `read_num` int NULL DEFAULT NULL COMMENT '阅读量',
  `cover_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '封面',
  `recommend` int NULL DEFAULT NULL COMMENT '博主推荐，0未推荐，1推荐',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `author_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_info
-- ----------------------------
INSERT INTO `t_blog_info` VALUES ('1992530184875667458', '子类和父类具有相同属性', '当子类和父类具有相同属性时，子类是否会覆盖父类属性的值？', '1992490036519628801', '1992494188419215361', 0, 44, '5af64f6ede3c4cd8a37c7bb97d75c458.jpg', 1, '0', '1959249411166965761', '2025-11-23 17:46:30', '1959249411166965761', '2026-01-02 23:20:31', '云鲲');
INSERT INTO `t_blog_info` VALUES ('1992557797700706301', 'ElasticSearch文档元数据字段', 'Elasticsearch中的元数据字段，相当于mysql中的隐式字段row_id等', '1992489809792331778', '1992494030293954562', 1, 14, NULL, 0, '1', '1959249411166965761', '2025-11-23 11:36:14', '1959249411166965761', '2025-12-06 09:56:27', '云鲲');
INSERT INTO `t_blog_info` VALUES ('1992557797700706305', 'ElasticSearch文档元数据字段test', 'Elasticsearch中的元数据字段，相当于mysql中的隐式字段row_id等test', '1992488568651640833', '1992492258997432321,1992492172250836994,1992492292124045314', 0, 31, '6022239a9ccd4fa7b6da09ae984a6ddc.jpg', 1, '0', '1959249411166965761', '2025-11-23 11:36:14', '1959249411166965761', '2025-12-07 15:53:08', '云鲲');
INSERT INTO `t_blog_info` VALUES ('2002202125576200193', '测试博客发布功能', '测试博客发布功能', '1992489159654240258', '1966845873975500802', 0, 0, 'http://localhost/api/file/download/e116594197ef4bb39890b7a724c6b98a.jpg', 1, '0', '1959249411166965761', '2025-12-20 10:19:21', '1959249411166965761', '2025-12-20 10:19:21', '云鲲');

-- ----------------------------
-- Table structure for t_blog_info_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_info_detail`;
CREATE TABLE `t_blog_info_detail`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `blog_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '博客id',
  `content` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '博客内容',
  `pic_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_info_detail
-- ----------------------------
INSERT INTO `t_blog_info_detail` VALUES ('1992530185005690882', '1992530184875667458', '# 成员变量\n父类定义 public，protected，private 三种权限的属性\n```java\npublic class Pepole {\n    public String publicName = \"parent-public-name\";\n    private String privateName = \"parent-private-name\";\n    protected String protectedName = \"parent-protected-name\";\n}\n```\n子类和父类定义一样的属性\n```java\npublic class Student extends Pepole{\n    public String publicName = \"child-public-name\";\n    private String privateName = \"child-private-name\";\n    protected String protectedName = \"child-protected-name\";\n}\n```\n## 创建子类对象\n#### 查看输出结果，依旧是访问的子类对象中的属性\n```java\npublic class Test {\n    public static void main(String[] args) {\n        Student stu = new Student();\n        System.out.println(\"创建子类 \" + stu.protectedName);\n        System.out.println(\"创建子类 \" + stu.publicName);\n    }\n}\n// 输出结果\n创建子类 child-protected-name\n创建子类 child-public-name\n```\n## 向上转型\n#### 可以看到它输出的是父类的属性\n```java\npublic class Test {\n    public static void main(String[] args) {\n        Pepole student = new Student();\n        System.out.println(\"向上转型 \" + student.protectedName);\n        System.out.println(\"向上转型 \" + student.publicName);\n    }\n}\n// 输出结果\n向上转型 parent-protected-name\n向上转型 parent-public-name\n```\n# 静态变量\n直接说结论，感觉没啥好测试的\n#### 首先静态变量属于类对象，在类加载之后就存在于方法区中，不会存在子类覆盖父类的问题\n# 总结\n- 首先子类属性是否会覆盖父类属性，取决于对象的类型\n  - 在成员变量的测试中，我们发现只有创建子类对象时，才会输出子类属性，而向上转型时，输出的父类属性。\n- 其次就是权限控制符，是不影响父类子类覆盖问题的\n- 静态变量不会被覆盖\n\n', NULL, '0', '1959249411166965761', '2025-11-23 17:46:30', '1959249411166965761', '2025-11-23 17:46:30');
INSERT INTO `t_blog_info_detail` VALUES ('1992557797767815169', '1992557797700706305', '### _doc_count\n桶聚合时返回的字段doc_count，就依赖于该字段。_doc_count默认值是1，因此桶聚合时，有一个文档就加1。可以在mapping中为字段设置响应的数值，之后聚合时会加上该值。\n### _field_names\n用于索引那些doc_values和norms被禁用的字段。\n作用未知\n### _ignored\n索引并存储那些被忽略的字段名称，参考mapping参数中的ignore相关配置\n### _id\n- 文档唯一标识\n- 可以在term、terms、match和query_string中使用\n- 大小限制为512字节\n- 不可以用于排序和聚合\n### _index\n_index字段是虚拟公开的，即它不会作为真实字段添加的Lucene索引中\n多用于跨多个索引进行查询\n```yaml\nGet /index_1,index_2/_search\n{\n  \"query\":{\n    \"terms\":{\n      \"_index\":[\"index_1\",\"index_2\"]\n    }\n  }\n}\n# 匹配索引名称为index_1和index_2的文档\n# 涉及远程索引时，必须使用冒号进行分割，如：cluster_1:index_3\n# 不然会匹配本地的索引\n```\n### _meta\n无用\n### _routing\n该字段用来影响文档路由到那个分片进行存储，默认的_routing的值是文档的_id\n```yaml\nrouting_factor = num_routing_shards / num_primary_shards\nshard_num = (hash(_routing) % num_routing_shards) / routing_factor\n# num_routing_shards 通过 index.number_of_routing_shards 来设置\n# num_primary_shards 通过 index.number_of_shards 来设置\n```\n可以通过如下方式来为文档设置routing\n```yaml\nPUT my-index-000001/_doc/1?routing=user1&refresh=true <1>\n{\n  \"title\": \"This is a document\"\n}\n# 同样获取的时候也需要提供相同的routing值\n# 好处就是，不需要将请求发送到所有的分片上\nGET my-index-000001/_doc/1?routing=user1\n```\n通过设置如下，来确保所有的CRUD操作都使用自定义routing值\n```yaml\nPUT my-index-000002\n{\n  \"mappings\": {\n    \"_routing\": {\n      \"required\": true\n    }\n  }\n}\n# 不使用的话，会抛异常：routing_missing_exception\n```\n#### 路由到索引分区\n可以使用自定义路由值路由到分片的一个子集而不是单个分片，有助于减轻集群不平衡的风险。\n设置：index.routing_partion_size，大于1且小于 index.number_of+shards\n```yaml\n分片号的计算方式\nrouting_value = hash(_routing) + hash(_id) % routing_partition_size\nshard_num = (routing_value % num_routing_shards) / routing_factor\n```\n### _source\n该字段存储索引时传入的原始的JSON文档\n- Synthetic Source：合成source，一种不存储原始的JSON到磁盘上，而是在检索的时候重建源内容。这种不可能使用的\n- 禁用_source：说实话也不太可能\n- 排除和包含_source中的字段：也不常用\n##### _tier\n与节点的角色进行绑定，查询时添加相应参数，可以提高查询效率\n```yaml\nGET index_1,index_2/_search\n{\n  \"query\": {\n    \"terms\": {\n      \"_tier\": [\"data_hot\", \"data_warm\"]\n    }\n  }\n}\n```\n', NULL, '0', '1959249411166965761', '2025-11-23 11:36:14', '1959249411166965761', '2025-11-23 11:36:14');
INSERT INTO `t_blog_info_detail` VALUES ('2002202125706223617', '2002202125576200193', '测试博客发布功能', 'http://localhost/api/file/download/e116594197ef4bb39890b7a724c6b98a.jpg', '0', '1959249411166965761', '2025-12-20 10:19:21', '1959249411166965761', '2025-12-20 10:19:21');

-- ----------------------------
-- Table structure for t_blog_label
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_label`;
CREATE TABLE `t_blog_label`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `label_name` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `category_id` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类id',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_label
-- ----------------------------
INSERT INTO `t_blog_label` VALUES ('1992492172250836994', 'Spring', '1992488568651640833', '0', '1959249411166965761', '2025-11-23 15:15:27', '1959249411166965761', '2025-11-23 15:15:27');
INSERT INTO `t_blog_label` VALUES ('1992492258997432321', 'Spring MVC', '1992488568651640833', '0', '1959249411166965761', '2025-11-23 15:15:48', '1959249411166965761', '2025-11-23 15:15:48');
INSERT INTO `t_blog_label` VALUES ('1992492292124045314', 'Spring Boot', '1992488568651640833', '0', '1959249411166965761', '2025-11-23 15:15:56', '1959249411166965761', '2025-11-23 15:15:56');
INSERT INTO `t_blog_label` VALUES ('1992492816554651650', 'RabbitMQ', '1992489130247974914', '0', '1959249411166965761', '2025-11-23 15:18:01', '1959249411166965761', '2025-11-23 15:18:01');
INSERT INTO `t_blog_label` VALUES ('1992492861370789889', 'Kafka', '1992489130247974914', '0', '1959249411166965761', '2025-11-23 15:18:12', '1959249411166965761', '2025-11-23 15:18:12');
INSERT INTO `t_blog_label` VALUES ('1992492898846896130', 'RocketMQ', '1992489130247974914', '0', '1959249411166965761', '2025-11-23 15:18:20', '1959249411166965761', '2025-11-23 15:18:20');
INSERT INTO `t_blog_label` VALUES ('1992492943256186882', 'ActiveMQ', '1992489130247974914', '0', '1959249411166965761', '2025-11-23 15:18:31', '1959249411166965761', '2025-11-23 15:18:31');
INSERT INTO `t_blog_label` VALUES ('1992493142028447745', 'Redis', '1992489159654240258', '0', '1959249411166965761', '2025-11-23 15:19:18', '1959249411166965761', '2025-11-23 15:19:18');
INSERT INTO `t_blog_label` VALUES ('1992493240753975298', 'Memcached', '1992489159654240258', '0', '1959249411166965761', '2025-11-23 15:19:42', '1959249411166965761', '2025-11-23 15:19:42');
INSERT INTO `t_blog_label` VALUES ('1992493263126392834', '本地缓存', '1992489159654240258', '0', '1959249411166965761', '2025-11-23 15:19:47', '1959249411166965761', '2025-11-23 15:19:47');
INSERT INTO `t_blog_label` VALUES ('1992493282684432386', '分布式缓存', '1992489159654240258', '0', '1959249411166965761', '2025-11-23 15:19:52', '1959249411166965761', '2025-11-23 15:19:52');
INSERT INTO `t_blog_label` VALUES ('1992493557545562114', 'Mysql', '1992489461782540289', '0', '1959249411166965761', '2025-11-23 15:20:58', '1959249411166965761', '2025-11-23 15:20:58');
INSERT INTO `t_blog_label` VALUES ('1992493647999922177', '索引优化', '1992489461782540289', '0', '1959249411166965761', '2025-11-23 15:21:19', '1959249411166965761', '2025-11-23 15:21:19');
INSERT INTO `t_blog_label` VALUES ('1992493674415648770', 'SQL优化', '1992489461782540289', '0', '1959249411166965761', '2025-11-23 15:21:25', '1959249411166965761', '2025-11-23 15:21:25');
INSERT INTO `t_blog_label` VALUES ('1992493722918580225', '分库分表', '1992489461782540289', '0', '1959249411166965761', '2025-11-23 15:21:37', '1959249411166965761', '2025-11-23 15:21:37');
INSERT INTO `t_blog_label` VALUES ('1992493816111820801', 'MongoDB', '1992489461782540289', '0', '1959249411166965761', '2025-11-23 15:21:59', '1959249411166965761', '2025-11-23 15:21:59');
INSERT INTO `t_blog_label` VALUES ('1992494030293954562', 'Elasticsearch', '1992489809792331778', '0', '1959249411166965761', '2025-11-23 15:22:50', '1959249411166965761', '2025-11-23 15:22:50');
INSERT INTO `t_blog_label` VALUES ('1992494055963095042', 'Solr', '1992489809792331778', '0', '1959249411166965761', '2025-11-23 15:22:56', '1959249411166965761', '2025-11-23 15:22:56');
INSERT INTO `t_blog_label` VALUES ('1992494082114580481', 'Lucene', '1992489809792331778', '0', '1959249411166965761', '2025-11-23 15:23:03', '1959249411166965761', '2025-11-23 15:23:03');
INSERT INTO `t_blog_label` VALUES ('1992494188419215361', 'Java', '1992490036519628801', '0', '1959249411166965761', '2025-11-23 15:23:28', '1959249411166965761', '2025-11-23 15:23:28');
INSERT INTO `t_blog_label` VALUES ('1992494211366252546', 'Python', '1992490036519628801', '0', '1959249411166965761', '2025-11-23 15:23:33', '1959249411166965761', '2025-11-23 15:23:33');
INSERT INTO `t_blog_label` VALUES ('1992494233025638401', 'Go', '1992490036519628801', '0', '1959249411166965761', '2025-11-23 15:23:39', '1959249411166965761', '2025-11-23 15:23:39');
INSERT INTO `t_blog_label` VALUES ('1992494264365477890', 'C++', '1992490036519628801', '0', '1959249411166965761', '2025-11-23 15:23:46', '1959249411166965761', '2025-11-23 15:23:46');
INSERT INTO `t_blog_label` VALUES ('1992494292358262785', 'PHP', '1992490036519628801', '0', '1959249411166965761', '2025-11-23 15:23:53', '1959249411166965761', '2025-11-23 15:23:53');
INSERT INTO `t_blog_label` VALUES ('1992494340617924609', 'Node.js', '1992490036519628801', '0', '1959249411166965761', '2025-11-23 15:24:04', '1959249411166965761', '2025-11-23 15:24:04');
INSERT INTO `t_blog_label` VALUES ('1992494685423267842', 'Spring Cloud', '1992490935300255745', '0', '1959249411166965761', '2025-11-23 15:25:26', '1959249411166965761', '2025-11-23 15:25:26');
INSERT INTO `t_blog_label` VALUES ('1992494726485504001', 'Dubbo', '1992490935300255745', '0', '1959249411166965761', '2025-11-23 15:25:36', '1959249411166965761', '2025-11-23 15:25:36');
INSERT INTO `t_blog_label` VALUES ('1992494761730240514', 'gRPC', '1992490935300255745', '0', '1959249411166965761', '2025-11-23 15:25:45', '1959249411166965761', '2025-11-23 15:25:45');
INSERT INTO `t_blog_label` VALUES ('1992495006102974465', '分布式', '1992490935300255745', '0', '1959249411166965761', '2025-11-23 15:26:43', '1959249411166965761', '2025-11-23 15:26:43');
INSERT INTO `t_blog_label` VALUES ('1992495564499054594', 'JVM调优', '1992495103356301314', '0', '1959249411166965761', '2025-11-23 15:28:56', '1959249411166965761', '2025-11-23 15:28:56');
INSERT INTO `t_blog_label` VALUES ('1992495632375476225', '数据库优化', '1992495103356301314', '0', '1959249411166965761', '2025-11-23 15:29:12', '1959249411166965761', '2025-11-23 15:29:12');
INSERT INTO `t_blog_label` VALUES ('1992495653640597506', '缓存优化', '1992495103356301314', '0', '1959249411166965761', '2025-11-23 15:29:17', '1959249411166965761', '2025-11-23 15:29:17');
INSERT INTO `t_blog_label` VALUES ('1994580936045400066', '缓存优化-test', '1992495103356301314', '0', '1959249411166965761', '2025-11-29 09:35:27', '1959249411166965761', '2025-11-29 09:35:27');

-- ----------------------------
-- Table structure for t_blog_recommend
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_recommend`;
CREATE TABLE `t_blog_recommend`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `related_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '关联id',
  `introduction` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `category_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类别id',
  `type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '0-默认博客，1-面经，2-项目，3-知识库',
  `weight` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权重',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_recommend
-- ----------------------------
INSERT INTO `t_blog_recommend` VALUES ('1992530185328652289', '子类和父类具有相同属性', '1992530184875667458', '当子类和父类具有相同属性时，子类是否会覆盖父类属性的值？', '1992490036519628801', '0', 'medium', '1', '1959249411166965761', '2025-11-23 17:46:30', '1959249411166965761', '2025-11-23 17:46:30');
INSERT INTO `t_blog_recommend` VALUES ('1992557797801369602', 'ElasticSearch文档元数据字段', '1992557797700706305', 'Elasticsearch中的元数据字段，相当于mysql中的隐式字段row_id等', '1992489809792331778', '0', 'medium', '0', '1959249411166965761', '2025-11-23 11:36:14', '1959249411166965761', '2025-11-23 11:36:14');
INSERT INTO `t_blog_recommend` VALUES ('2002202125836247042', '测试博客发布功能', '2002202125576200193', '测试博客发布功能', '1966843250455093250', '0', 'medium', '0', '1959249411166965761', '2025-12-20 10:19:21', '1959249411166965761', '2025-12-20 10:19:21');

-- ----------------------------
-- Table structure for t_blog_settings
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_settings`;
CREATE TABLE `t_blog_settings`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `blog_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '博客名称',
  `author` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  `introduction` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '作者介绍',
  `avatar` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '作者头像',
  `github_home` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'GITHUB地址',
  `csdn_home` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'CSDN地址',
  `gitee_home` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'GITEE地址',
  `zhihu_home` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知乎地址',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_settings
-- ----------------------------
INSERT INTO `t_blog_settings` VALUES ('1', '云博客', '云鲲', '面朝大海，春暖花开', 'http://14.103.150.52/api/file/download/e116594197ef4bb39890b7a724c6b98a.jpg', NULL, 'https://blog.csdn.net/ssj3070863284?spm=1000.2115.3001.5343', 'https://gitee.com/sunshijie123456', 'https://www.zhihu.com/people/yun-kun-44', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `t_knowledge`;
CREATE TABLE `t_knowledge`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知识库名称',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知识库标题',
  `introduction` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知识库介绍',
  `cover_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知识库封面',
  `is_public` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否公开，0私有，1公开',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_knowledge
-- ----------------------------

-- ----------------------------
-- Table structure for t_knowledge_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_knowledge_detail`;
CREATE TABLE `t_knowledge_detail`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `knowledge_info_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知识库信息关联id',
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文章内容',
  `pic_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_knowledge_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_knowledge_info
-- ----------------------------
DROP TABLE IF EXISTS `t_knowledge_info`;
CREATE TABLE `t_knowledge_info`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `knowledge_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '知识库关联id',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分组名称，或文章名称',
  `parent_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上级id',
  `type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '1分组，2文章',
  `level` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '层级',
  `is_public` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否公开，0私有，1公开',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_knowledge_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `permission_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1959248032939642882', '用户信息查询权限', 'userinfo.query', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_permission` VALUES ('1959248134626349057', '用户信息新增权限', 'userinfo.insert', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_permission` VALUES ('1959248191475945474', '用户信息删除权限', 'userinfo.delete', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_permission` VALUES ('1964186818035720193', '用户信息更新权限', 'userinfo.update', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_permission` VALUES ('1966764154224144386', '文章信息查询', 'blog.query', '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `use_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '使用状态',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除状态;0-未删除，1-已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1957017882806194177', '管理员/博主', 'admin', NULL, '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('1959248352587550721', '管理员/博主', 'admin', NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('1966763246174105601', '普通用户', 'COMMON_USER', NULL, '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除状态;0-未删除，1-已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1959249022363373569', '1959248352587550721', '1959248032939642882', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_role_permission` VALUES ('1959249022363373570', '1959248352587550721', '1959248134626349057', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_role_permission` VALUES ('1959249022363373571', '1959248352587550721', '1959248191475945474', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_role_permission` VALUES ('1966764530373521410', '1966763246174105601', '1966764154224144386', '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `user_account` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `signature` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户签名',
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `role_code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `del_status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除标识，0未删除，1已删除',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1959176099581702145', 'yunkun', '1234', '云鲲', '越努力越幸运', '3070863284@qq.com', NULL, 'COMMON_USER', '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_user_info` VALUES ('1959177270539145218', 'yunkun', '81dc9bdb52d04dc20036dbd8313ed055', '云鲲', '越努力越幸运', '3070863284@qq.com', NULL, 'COMMON_USER', '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_user_info` VALUES ('1959178713018982401', 'yunkun', '81dc9bdb52d04dc20036dbd8313ed055', '云鲲', '越努力越幸运', '3070863284@qq.com', '测试头像.jpg', 'COMMON_USER', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_user_info` VALUES ('1959249411166965761', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '云鲲', '越努力越幸运', '3070863284@qq.com', 'e116594197ef4bb39890b7a724c6b98a.jpg', 'admin', '0', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
