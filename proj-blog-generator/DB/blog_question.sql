/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : blog_question

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 16/10/2021 21:08:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog_question
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_question`;
CREATE TABLE `t_blog_question`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者用户id',
  `nick_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者用户昵称',
  `user_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者头像url',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题标题',
  `md_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'md问题内容',
  `html_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'html问题内容',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '浏览次数',
  `thumhup` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `reply` int(11) NULL DEFAULT 0 COMMENT '回复数',
  `status` tinyint(3) NULL DEFAULT 1 COMMENT '状态，0：已删除， 1：未解决，2：已解决',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '问题信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_question
-- ----------------------------
INSERT INTO `t_blog_question` VALUES ('1', 'string', 'sxxxtringstring', 'string', 'string', 'sxxxtringstrxxxxxingstring', 'stringstringstringstring', 19, 0, 1, 1, '2020-04-08 09:54:03', '2020-05-20 20:48:59');
INSERT INTO `t_blog_question` VALUES ('1247704928695230466', 'string', 'stringstring', 'string', 'string', 'stringstringstring', 'stringstringstringstring', 10, 0, 0, 1, '2020-04-08 09:54:03', '2020-05-20 12:31:22');
INSERT INTO `t_blog_question` VALUES ('1253338630146195458', '9', '梦学谷', NULL, 'string', 'string', 'string', 0, 0, 0, 1, '2020-04-23 21:29:26', '2020-04-23 21:29:26');
INSERT INTO `t_blog_question` VALUES ('1263083780565028866', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '123123', 'asdfasdfsdfasdasdad![aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg](https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/0fd196b1bbdd4dcb85523618423d43ca.jpeg)', '<p>asdfasdfsdfasdasdad<img src=\"https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/0fd196b1bbdd4dcb85523618423d43ca.jpeg\" alt=\"aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg\" /></p>\n', 11, 0, 1, 1, '2020-05-20 20:26:54', '2020-05-20 20:32:22');
INSERT INTO `t_blog_question` VALUES ('1263084075219079170', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'asdfs', 'asdfsdf', '<p>asdfsdf</p>\n', 0, 0, 0, 1, '2020-05-20 20:28:05', '2020-05-20 20:28:05');
INSERT INTO `t_blog_question` VALUES ('1263084121499029506', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'asdfs', 'asdfsdf', '<p>asdfsdf</p>\n', 0, 0, 0, 1, '2020-05-20 20:28:16', '2020-05-20 20:28:16');
INSERT INTO `t_blog_question` VALUES ('1263084554871296002', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'asdasdas', 'asdasdasd', '<p>asdasdasd</p>\n', 9, 0, 0, 1, '2020-05-20 20:29:59', '2020-05-20 20:45:52');
INSERT INTO `t_blog_question` VALUES ('1263117581294948353', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'asdfsdfxxxxxxx', 'asdfsdfxxxxx', '<p>asdfsdfxxxxx</p>\n', 15, 0, 7, 1, '2020-05-20 22:41:13', '2020-05-20 22:48:21');
INSERT INTO `t_blog_question` VALUES ('1263120412383039489', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '修改 api 接口-----520', '\n# 接口问题\n1. 修改 api 接口，`nuxt.config.js` 引入api插件\n2. 修改组件调用\n\n# 文章和问答组件\n1. 新增和修改文章时，带上用户信息提交\n2. 新增和修改问答时，带上用户信息提交\n3. 文章和阅读评论区带上用户信息提交\n4. 阅读和点赞保存到cookie中\n\n# 后台\n1. \n\n\n![aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg](https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/455565cc65c54ed5af184e58b11ca447.jpeg)\nalert(1)\n', '<h1><a id=\"_1\"></a>接口问题</h1>\n<ol>\n<li>修改 api 接口，<code>nuxt.config.js</code> 引入api插件</li>\n<li>修改组件调用</li>\n</ol>\n<h1><a id=\"_5\"></a>文章和问答组件</h1>\n<ol>\n<li>新增和修改文章时，带上用户信息提交</li>\n<li>新增和修改问答时，带上用户信息提交</li>\n<li>文章和阅读评论区带上用户信息提交</li>\n<li>阅读和点赞保存到cookie中</li>\n</ol>\n<h1><a id=\"_11\"></a>后台</h1>\n<ol>\n<li></li>\n</ol>\n<p><img src=\"https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/455565cc65c54ed5af184e58b11ca447.jpeg\" alt=\"aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg\" /><br />\nalert(1)</p>\n', 26, 3, 1, 1, '2020-05-20 22:52:28', '2020-05-21 10:29:19');
INSERT INTO `t_blog_question` VALUES ('1263469089899282433', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0521完成整体修改', '0521', '<p>0521</p>\n', 2, 0, 1, 1, '2020-05-21 21:57:59', '2020-05-21 22:10:42');
INSERT INTO `t_blog_question` VALUES ('2', '9', NULL, NULL, 'java底层实现1111xxxxsss', '# 参考官网配置：\n[https://cli.vuejs.org/zh/guid...](https://cli.vuejs.org/zh/guide/html-and-static-assets.html#public-文件夹)\n## 二号标签\n需要设置`BASE_URL````jsdata () {  return {    publicPath: process.env.BASE_URL  }}```然后```html<img :src=\"`${publicPath}my-image.png`\">```', '<h1><a id=\"_0\"></a>参考官网配置：</h1>\n<p><a href=\"https://cli.vuejs.org/zh/guide/html-and-static-assets.html#public-%E6%96%87%E4%BB%B6%E5%A4%B9\" target=\"_blank\">https://cli.vuejs.org/zh/guid…</a></p>\n<h2><a id=\"_2\"></a>二号标签</h2>\n<p>需要设置<code>BASE_URL````jsdata () {  return {    publicPath: process.env.BASE_URL  }}```然后```html&lt;img :src=&quot;</code>${publicPath}my-image.png`&quot;&gt;```</p>\n', 100, 5, 1, 1, '2020-05-21 22:13:01', '2020-05-21 22:13:01');
INSERT INTO `t_blog_question` VALUES ('3', '9', NULL, NULL, 'html底层实现', 'html底层实现是什么', 'html底层实现是什么', 20, 10, 5, 1, '2010-04-07 17:13:08', '2020-05-20 22:56:40');
INSERT INTO `t_blog_question` VALUES ('string', 'string', 'string', 'string', 'string', 'string', 'string', 0, 0, 0, 0, '2020-04-23 21:29:26', '2020-04-23 23:02:26');

-- ----------------------------
-- Table structure for t_blog_question_label
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_question_label`;
CREATE TABLE `t_blog_question_label`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `question_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章 id',
  `label_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签id',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '问题标签中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_question_label
-- ----------------------------
INSERT INTO `t_blog_question_label` VALUES ('1247704928707813377', '1247704928695230466', '1', '2020-04-08 09:56:50');
INSERT INTO `t_blog_question_label` VALUES ('1247706372177534978', '1', '1', '2020-04-08 10:02:35');
INSERT INTO `t_blog_question_label` VALUES ('1247706372177534979', '1', '2', '2020-04-08 10:02:35');
INSERT INTO `t_blog_question_label` VALUES ('1263084075231662081', '1263084075219079170', '8', '2020-05-20 20:28:05');
INSERT INTO `t_blog_question_label` VALUES ('1263084075231662082', '1263084075219079170', '9', '2020-05-20 20:28:05');
INSERT INTO `t_blog_question_label` VALUES ('1263084075231662083', '1263084075219079170', '10', '2020-05-20 20:28:05');
INSERT INTO `t_blog_question_label` VALUES ('1263084121507418113', '1263084121499029506', '8', '2020-05-20 20:28:16');
INSERT INTO `t_blog_question_label` VALUES ('1263084121507418114', '1263084121499029506', '9', '2020-05-20 20:28:16');
INSERT INTO `t_blog_question_label` VALUES ('1263084121507418115', '1263084121499029506', '10', '2020-05-20 20:28:16');
INSERT INTO `t_blog_question_label` VALUES ('1263084498579542017', '1263083780565028866', '1', '2020-05-20 20:29:46');
INSERT INTO `t_blog_question_label` VALUES ('1263088532715655169', '1263084554871296002', '2', '2020-05-20 20:45:47');
INSERT INTO `t_blog_question_label` VALUES ('1263088532715655170', '1263084554871296002', '3', '2020-05-20 20:45:47');
INSERT INTO `t_blog_question_label` VALUES ('1263088532715655171', '1263084554871296002', '6', '2020-05-20 20:45:47');
INSERT INTO `t_blog_question_label` VALUES ('1263088532715655172', '1263084554871296002', '5', '2020-05-20 20:45:47');
INSERT INTO `t_blog_question_label` VALUES ('1263117581307531266', '1263117581294948353', '8', '2020-05-20 22:41:13');
INSERT INTO `t_blog_question_label` VALUES ('1263117581307531267', '1263117581294948353', '9', '2020-05-20 22:41:13');
INSERT INTO `t_blog_question_label` VALUES ('1263117581307531268', '1263117581294948353', '10', '2020-05-20 22:41:13');
INSERT INTO `t_blog_question_label` VALUES ('1263121956901933058', '1263120412383039489', '4', '2020-05-20 22:58:36');
INSERT INTO `t_blog_question_label` VALUES ('1263472287485657089', '1263469089899282433', '19', '2020-05-21 22:10:42');
INSERT INTO `t_blog_question_label` VALUES ('1263472287485657090', '1263469089899282433', '18', '2020-05-21 22:10:42');
INSERT INTO `t_blog_question_label` VALUES ('1263472287485657091', '1263469089899282433', '20', '2020-05-21 22:10:42');
INSERT INTO `t_blog_question_label` VALUES ('1263472868644225025', '2', '7', '2020-05-21 22:13:00');
INSERT INTO `t_blog_question_label` VALUES ('1263472868644225026', '2', '11', '2020-05-21 22:13:00');
INSERT INTO `t_blog_question_label` VALUES ('1263472868644225027', '2', '10', '2020-05-21 22:13:00');

-- ----------------------------
-- Table structure for t_blog_replay
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_replay`;
CREATE TABLE `t_blog_replay`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '-1 表示正常回答，其他值表示是回答的回复',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回答者id',
  `nick_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回答者用户昵称',
  `user_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回答者头像url',
  `question_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题id',
  `md_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'md问题内容',
  `html_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'html问题内容',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '回答信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_replay
-- ----------------------------
INSERT INTO `t_blog_replay` VALUES ('1263082769691627522', '-1', NULL, NULL, NULL, '1', 'sdfsdf', '<p>sdfsdf</p>\n', '2020-05-20 20:22:53');
INSERT INTO `t_blog_replay` VALUES ('1263084383596892161', '-1', NULL, NULL, NULL, '1263083780565028866', 'ASDASD', '<p>ASDASD</p>\n', '2020-05-20 20:29:18');
INSERT INTO `t_blog_replay` VALUES ('1263118982020517890', '-1', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263117581294948353', '|column1|column2|column3|\n|-|-|-|\n|content1|content2|content3|\n', '<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n', '2020-05-20 22:46:47');
INSERT INTO `t_blog_replay` VALUES ('1263119279073710082', '-1', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263117581294948353', '# 一级标题', '<h1><a id=\"_0\"></a>一级标题</h1>\n', '2020-05-20 22:47:58');
INSERT INTO `t_blog_replay` VALUES ('1263119309906038785', '-1', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263117581294948353', '## 二级标题', '<h2><a id=\"_0\"></a>二级标题</h2>\n', '2020-05-20 22:48:05');
INSERT INTO `t_blog_replay` VALUES ('1263119415938043906', '1263119309906038785', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263117581294948353', NULL, '[微笑]', '2020-05-20 22:48:31');
INSERT INTO `t_blog_replay` VALUES ('1263119476650594305', '1263119309906038785', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263117581294948353', NULL, '这个回答不错', '2020-05-20 22:48:45');
INSERT INTO `t_blog_replay` VALUES ('1263120769792266241', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263120412383039489', '520记录明天', '<p>520记录明天</p>\n', '2020-05-20 22:53:53');
INSERT INTO `t_blog_replay` VALUES ('1263469857066844162', '-1', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263469089899282433', 'asdfsdf', '<p>asdfsdf</p>\n', '2020-05-21 22:01:02');
INSERT INTO `t_blog_replay` VALUES ('1263474773026013185', '-1', '9', '梦学谷', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2', '|column1|column2|column3|\n|-|-|-|\n|content1|content2|content3|\n', '<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n', '2020-05-21 22:20:34');
INSERT INTO `t_blog_replay` VALUES ('1288311186024198145', '-1', '9', '梦学谷', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200522/8665d73ae2484bd28bc2ff4103538385.png', '3', '|column1|column2|column3|\n|-|-|-|\n|content1|content2|content3|\n', '<table>\n<thead>\n<tr>\n<th>column1</th>\n<th>column2</th>\n<th>column3</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>content1</td>\n<td>content2</td>\n<td>content3</td>\n</tr>\n</tbody>\n</table>\n', '2020-07-29 11:11:37');

SET FOREIGN_KEY_CHECKS = 1;
