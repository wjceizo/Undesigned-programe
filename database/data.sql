/*
 Navicat Premium Data Transfer

 Source Server         : springboot
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : data

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 12/06/2022 13:34:36
*/
CREATE DATABASE self_control_educational DEFAULT CHARACTER SET utf8; 

USE self_control_educational; 

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activitys
-- ----------------------------
DROP TABLE IF EXISTS `activitys`;
CREATE TABLE `activitys`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动标题',
  `intro` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动详情',
  `loc` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动地点',
  `comm` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动要求',
  `create_time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activitys
-- ----------------------------
INSERT INTO `activitys` VALUES ('1631521792948', '安全教育', '安全教育', '学校大礼堂', '全员参加', '2022-06-10');

-- ----------------------------
-- Table structure for classrooms
-- ----------------------------
DROP TABLE IF EXISTS `classrooms`;
CREATE TABLE `classrooms`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室名称',
  `loc` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室位置',
  `comm` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教室信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classrooms
-- ----------------------------
INSERT INTO `classrooms` VALUES ('1631521792941', '实验室(一)', '玉衡楼四楼', '计算机实验室');
INSERT INTO `classrooms` VALUES ('1631521792942', '实验室(二)', '玉衡楼四楼', '计算机实验室');
INSERT INTO `classrooms` VALUES ('1631521792943', '教室(一)', '天枢楼二楼', '授课室');
INSERT INTO `classrooms` VALUES ('1631521792944', '教室(二)', '天璇楼二楼', '授课室');
INSERT INTO `classrooms` VALUES ('1631521792945', '教室(三)', '开阳楼四楼', '授课室');

-- ----------------------------
-- Table structure for grades
-- ----------------------------
DROP TABLE IF EXISTS `grades`;
CREATE TABLE `grades`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名次',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grades
-- ----------------------------
INSERT INTO `grades` VALUES ('1631521792932', '19软件四', '2021-07-08 08:15:23');
INSERT INTO `grades` VALUES ('1631521792933', '19软件三', '2021-07-08 08:15:23');
INSERT INTO `grades` VALUES ('1631521792934', '18物联网二', '2021-07-08 08:15:23');
INSERT INTO `grades` VALUES ('1631521792935', '18计科一', '2021-07-08 08:15:23');

-- ----------------------------
-- Table structure for notices
-- ----------------------------
DROP TABLE IF EXISTS `notices`;
CREATE TABLE `notices`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知标题',
  `cont` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知内容',
  `start_time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开始时间',
  `end_time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统通知' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notices
-- ----------------------------
INSERT INTO `notices` VALUES ('1631521792946', '落实安全教育', '落实安全教育', '2022-06-15', '2029-10-08');
INSERT INTO `notices` VALUES ('1631521792947', '端午放假安排', '端午放假安排3天假期', '2022-06-03', '2022-06-05');

-- ----------------------------
-- Table structure for project_plans
-- ----------------------------
DROP TABLE IF EXISTS `project_plans`;
CREATE TABLE `project_plans`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `work_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作ID',
  `room_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室ID',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `work_id`(`work_id`) USING BTREE,
  INDEX `room_id`(`room_id`) USING BTREE,
  CONSTRAINT `project_plans_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `teacher_work` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `project_plans_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `classrooms` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '上课安排' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_plans
-- ----------------------------
INSERT INTO `project_plans` VALUES ('1631521792957', '1631521792954', '1631521792943', '2022-06-01 08:30:00');
INSERT INTO `project_plans` VALUES ('1631521792958', '1631521792956', '1631521792943', '2022-06-01 09:30:00');
INSERT INTO `project_plans` VALUES ('1631521792959', '1631521792955', '1631521792944', '2022-06-01 16:30:00');

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects`  (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of projects
-- ----------------------------
INSERT INTO `projects` VALUES ('1631521792936', 'Web开发', '2022-06-08 08:15:23');
INSERT INTO `projects` VALUES ('1631521792937', '软件工程', '2022-06-08 08:15:23');
INSERT INTO `projects` VALUES ('1631521792938', '操作系统', '2022-06-08 08:15:23');
INSERT INTO `projects` VALUES ('1631521792939', '计算机网络', '2022-06-08 08:15:23');
INSERT INTO `projects` VALUES ('1631521792940', '大学生就业安排', '2022-06-08 08:15:23');
INSERT INTO `projects` VALUES ('1654580723563', 'Java程序设计', '2022-06-09 13:45:23');

-- ----------------------------
-- Table structure for qiandao
-- ----------------------------
DROP TABLE IF EXISTS `qiandao`;
CREATE TABLE `qiandao`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `stu_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grades_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT NULL,
  `ischeck` int(11) NULL DEFAULT NULL,
  `checktime` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addtime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qiandao
-- ----------------------------
INSERT INTO `qiandao` VALUES ('1655005485753', '1631521792952', '1631521792936', '1631521792933', '1631521792950', 1, 1, '2022-06-12 11:45:02', '2022-06-12 11:44:45');
INSERT INTO `qiandao` VALUES ('1655007476669', '1631521792952', '1631521792936', '1631521792933', '1631521792950', 1, 0, NULL, '2022-06-12 12:17:56');
INSERT INTO `qiandao` VALUES ('1655007987134', '1631521792952', '1631521792936', '1631521792933', '1631521792950', 1, 1, '2022-06-12 12:26:46', '2022-06-12 12:26:27');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `grade_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在班级',
  `into_time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入学时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `grade_id`(`grade_id`) USING BTREE,
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `students_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1631521792951', '1631521792932', '2020-09-12');
INSERT INTO `students` VALUES ('1631521792952', '1631521792933', '2020-09-12');
INSERT INTO `students` VALUES ('1631521792953', '1631521792934', '2019-09-12');

-- ----------------------------
-- Table structure for teacher_work
-- ----------------------------
DROP TABLE IF EXISTS `teacher_work`;
CREATE TABLE `teacher_work`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `grade_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级ID',
  `project_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程ID',
  `teacher_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师ID',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `grade_id`(`grade_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `teacher_work_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_work_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_work_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授课安排' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_work
-- ----------------------------
INSERT INTO `teacher_work` VALUES ('1631521792954', '1631521792932', '1631521792936', '1631521792950', '2022-06-03 10:10:10');
INSERT INTO `teacher_work` VALUES ('1631521792955', '1631521792933', '1631521792936', '1631521792950', '2022-06-04 10:10:10');
INSERT INTO `teacher_work` VALUES ('1631521792956', '1631521792934', '1631521792936', '1631521792950', '2022-06-05 10:10:10');
INSERT INTO `teacher_work` VALUES ('1654580863502', '1631521792933', '1631521792936', '1631521792950', '2022-06-07 13:47:43');

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师信息',
  `record` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师学历',
  `job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师职称',
  `into_time` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入职时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachers
-- ----------------------------
INSERT INTO `teachers` VALUES ('1631521792950', '本科', '优秀教师', '2020-01-05');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `pass_word` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `age` int(11) NOT NULL COMMENT '用户年龄',
  `type` int(11) NOT NULL COMMENT '用户身份',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户年龄',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1631521792949', 'admin', 'admin', '明明上', 38, 0, '男', '2022-06-03 12:23:23');
INSERT INTO `users` VALUES ('1631521792950', 'fater', 'fater', '李云义', 28, 1, '男', '2022-06-03 12:23:23');
INSERT INTO `users` VALUES ('1631521792951', 'zhangwuji', 'zhangwuji', '张无忌', 18, 2, '男', '2022-06-03 12:23:23');
INSERT INTO `users` VALUES ('1631521792952', 'zhaomin', 'zhaomin', '赵敏', 18, 2, '女', '2022-06-03 12:23:23');
INSERT INTO `users` VALUES ('1631521792953', 'zhouzhiruo', 'zhouzhiruo', '周芷若', 19, 2, '女', '2022-06-03 12:23:23');

SET FOREIGN_KEY_CHECKS = 1;
