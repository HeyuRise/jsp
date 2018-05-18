/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : jsp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-18 15:27:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for action_log
-- ----------------------------
DROP TABLE IF EXISTS `action_log`;
CREATE TABLE `action_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_code` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL COMMENT 'eda系统会员号',
  `action` varchar(255) NOT NULL COMMENT '操作类型',
  `value` varchar(10000) DEFAULT NULL COMMENT '记录值',
  `param1` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `param2` varchar(255) DEFAULT NULL,
  `param3` varchar(255) DEFAULT NULL,
  `param4` varchar(255) DEFAULT NULL,
  `record_time` datetime NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`),
  KEY `index_count` (`record_time`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_log
-- ----------------------------

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cfg_name` varchar(255) NOT NULL COMMENT '记录名称',
  `value_str` varchar(255) DEFAULT NULL COMMENT '字符串值',
  `value_int` int(11) DEFAULT NULL COMMENT '数字值',
  `value_time` datetime DEFAULT NULL COMMENT '时间值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `usable` int(11) DEFAULT '1' COMMENT '是否可用 1-可用 0-不可用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`cfg_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL COMMENT '类型',
  `inner_id` int(11) DEFAULT NULL COMMENT '内部系统id',
  `inner_code` varchar(255) DEFAULT NULL COMMENT '内部系统code',
  `value_str` varchar(2047) NOT NULL COMMENT '字符串值',
  `value_int` int(11) DEFAULT NULL COMMENT '数字值',
  `value_time` datetime DEFAULT NULL COMMENT '时间值',
  `param_str_1` varchar(255) DEFAULT NULL,
  `param_str_2` varchar(255) DEFAULT NULL,
  `param_int_1` int(11) DEFAULT NULL,
  `param_int2` int(11) DEFAULT NULL,
  `enable` int(11) DEFAULT '1' COMMENT '是否可用 1-可用 0-不可用',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `inner_create_time` datetime DEFAULT NULL COMMENT '内部创建时间',
  `inner_update_time` datetime DEFAULT NULL COMMENT '内部更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_type_id` (`type`,`inner_id`),
  KEY `index_type_code` (`type`,`inner_code`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', 'express_status', null, 'error', '无法配送', null, null, null, null, null, null, '1', null, null, null, '2017-09-09 11:10:22', '2017-09-21 09:39:44');
INSERT INTO `dictionary` VALUES ('2', 'express_status', null, 'wait.push', '顺丰确认中', null, null, null, null, null, null, '1', null, null, null, '2017-09-09 11:10:25', '2017-09-21 09:41:41');
INSERT INTO `dictionary` VALUES ('3', 'express_status', null, 'wait.pick.up', '待收货', null, null, null, null, null, null, '1', null, null, null, '2017-09-09 11:10:27', '2017-09-21 09:41:55');
INSERT INTO `dictionary` VALUES ('4', 'express_status', null, 'deleiver', '配送中', null, null, '50,51,46,41,43,30,31,44', '50', null, null, '1', null, null, null, '2017-09-09 11:10:29', '2017-12-05 11:27:07');
INSERT INTO `dictionary` VALUES ('5', 'express_status', null, 'received', '完成', null, null, '80,8000', null, null, null, '1', null, null, null, '2017-09-09 11:10:32', '2017-09-21 09:42:00');
INSERT INTO `dictionary` VALUES ('6', 'pay_method', '1', null, '寄付现结', null, null, null, null, null, null, '1', null, null, null, '2017-09-09 11:10:34', '2017-09-30 09:06:38');
INSERT INTO `dictionary` VALUES ('7', 'pay_method', '2', null, '收方付', null, null, null, null, null, null, '1', null, null, null, '2017-09-09 11:10:36', '2017-09-09 11:11:00');
INSERT INTO `dictionary` VALUES ('8', 'pay_method', '3', null, '第三方付', null, null, null, null, null, null, '1', null, null, null, '2017-09-09 11:10:39', '2017-09-09 11:11:03');
INSERT INTO `dictionary` VALUES ('9', 'pay_method', '4', null, '寄付月结', null, null, null, null, null, null, '1', null, null, null, '2017-09-14 16:21:12', '2017-09-15 16:30:44');
INSERT INTO `dictionary` VALUES ('10', 'button', '1', '', '快递单导出按钮', null, null, '', null, '5', null, '1', null, null, null, '2017-09-09 11:10:41', '2017-09-15 16:30:42');
INSERT INTO `dictionary` VALUES ('11', 'button', '2', null, '打印', null, null, null, null, '3', null, '1', null, null, null, '2017-09-09 11:10:43', '2017-09-15 16:30:36');
INSERT INTO `dictionary` VALUES ('12', 'button', '3', null, '收件人导出', null, null, null, null, '7', null, '1', null, null, null, '2017-09-09 11:10:46', '2017-09-15 16:30:33');
INSERT INTO `dictionary` VALUES ('13', 'button', '4', null, '寄件人导出', null, null, null, null, '9', null, '1', null, null, null, '2017-09-09 11:10:48', '2017-09-15 16:30:29');
INSERT INTO `dictionary` VALUES ('14', 'button', '5', null, '寄件人地址', null, null, null, null, '11', null, '1', null, null, null, '2017-09-09 11:10:50', '2017-09-15 16:30:27');
INSERT INTO `dictionary` VALUES ('15', 'button', '6', null, '用户新增', null, null, null, null, '15', null, '1', null, null, null, '2017-09-09 11:10:53', '2017-09-15 16:30:21');
INSERT INTO `dictionary` VALUES ('16', 'button', '7', null, '全国行政区导出', null, null, null, null, '13', null, '1', null, null, null, '2017-09-09 11:10:56', '2017-09-15 16:30:18');
INSERT INTO `dictionary` VALUES ('17', 'button', '8', null, '启用/禁用用户', null, null, null, null, '18', null, '1', null, null, null, '2017-09-09 11:10:58', '2017-09-15 16:30:16');
INSERT INTO `dictionary` VALUES ('18', 'button', '9', null, '用户角色配置', null, null, null, null, '19', null, '1', null, null, null, '2017-09-09 11:11:01', '2017-09-15 16:30:13');
INSERT INTO `dictionary` VALUES ('20', 'button', '11', null, '业务类型操作', null, null, null, null, '22', null, '1', null, null, null, '2017-09-11 08:57:19', '2017-09-22 09:42:00');
INSERT INTO `dictionary` VALUES ('21', 'button', '12', null, '增值服务启用/禁用', null, null, null, null, '25', null, '1', null, null, null, '2017-09-11 08:57:22', '2017-09-22 09:42:03');
INSERT INTO `dictionary` VALUES ('22', 'button', '13', null, '重置用户密码', null, null, null, null, '20', null, '1', null, null, null, '2017-09-22 09:48:38', '2017-09-22 09:49:26');
INSERT INTO `dictionary` VALUES ('23', 'cargo_name', null, '1', 'PCB', null, null, null, null, null, null, '1', null, null, null, '2017-09-21 09:54:41', '2017-09-22 09:49:14');
INSERT INTO `dictionary` VALUES ('24', 'cargo_name', null, '2', 'SMT', null, null, null, null, null, null, '1', null, null, null, '2017-09-21 09:54:44', '2017-09-22 09:49:10');
INSERT INTO `dictionary` VALUES ('25', 'cargo_name', null, '3', 'COL', null, null, null, null, null, null, '1', null, null, null, '2017-09-21 09:54:47', '2017-09-22 09:49:07');
INSERT INTO `dictionary` VALUES ('26', 'cargo_name', null, '4', '文件', null, null, null, null, null, null, '1', null, null, null, '2017-09-21 09:54:49', '2017-09-22 09:48:59');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL COMMENT '日志等级',
  `file_name` varchar(255) DEFAULT NULL COMMENT '产生日志所在的文件',
  `class_name` varchar(255) DEFAULT NULL COMMENT '产生日志所在的类',
  `method_name` varchar(255) DEFAULT NULL COMMENT '产生日志所在的方法',
  `line_number` int(11) DEFAULT NULL COMMENT '产生日志所在的行数',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '日志详细内容',
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_type` varchar(255) DEFAULT NULL COMMENT '权限id',
  `menu_level` int(11) DEFAULT '1' COMMENT '菜单等级  ',
  `main_menu_id` int(11) DEFAULT NULL COMMENT '主菜单id',
  `menu` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `param` varchar(255) DEFAULT NULL,
  `enable` int(11) DEFAULT '1' COMMENT '是否可用  1 可用， 0 不可用',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2', '1', '1', '新建快递单管理', null, '1', '&#xe859;', '1', '1', '2017-09-11 13:57:57', '2017-09-11 13:58:25');
INSERT INTO `menu` VALUES ('2', '2', '2', '1', '新建快递单', '/jsp/html/create_list.html', null, '', '1', '1', '2017-09-11 13:58:00', '2018-05-18 14:12:41');
INSERT INTO `menu` VALUES ('3', '4,5', '1', '2', '快递单管理', null, '2', '&#xe858;', '1', '2', '2017-09-11 13:58:02', '2017-09-11 13:58:29');
INSERT INTO `menu` VALUES ('4', '4,5', '2', '2', '快递单列表', '/jsp/html/Express_List.html', null, '', '1', '1', '2017-09-11 13:58:04', '2018-05-18 14:12:49');
INSERT INTO `menu` VALUES ('15', '14,15,18,19,20', '1', '5', '系统管理', null, '5', '&#xe8bc;', '1', '5', '2017-09-11 13:58:29', '2017-09-25 09:55:49');
INSERT INTO `menu` VALUES ('16', '14', '2', '5', '系统角色权限', '/jsp/html/role.html', null, null, '1', '1', '2017-09-11 13:58:31', '2018-05-18 14:12:55');
INSERT INTO `menu` VALUES ('17', '15,18,19,20', '2', '5', '用户权限管理', '/jsp/html/user.html', null, '', '1', '1', '2017-09-11 13:58:34', '2018-05-18 14:13:00');

-- ----------------------------
-- Table structure for record_utils
-- ----------------------------
DROP TABLE IF EXISTS `record_utils`;
CREATE TABLE `record_utils` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `record_name` varchar(255) NOT NULL COMMENT '记录名称',
  `value_str` varchar(255) DEFAULT NULL COMMENT '字符串值',
  `value_int` int(11) DEFAULT NULL COMMENT '数字值',
  `value_time` datetime DEFAULT NULL COMMENT '时间值',
  `param` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`record_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record_utils
-- ----------------------------
INSERT INTO `record_utils` VALUES ('7', 'last_reload_time', null, null, '2018-05-18 15:26:52', '', '上次缓存载入时间', '2018-05-18 15:26:51');

-- ----------------------------
-- Table structure for role_auth
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  `enable` int(11) NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_auth
-- ----------------------------
INSERT INTO `role_auth` VALUES ('1', '2', '1', '0', '2017-09-09 13:43:55', '2018-03-02 13:22:46');
INSERT INTO `role_auth` VALUES ('2', '2', '2', '1', '2017-09-09 13:43:58', '2017-09-09 13:44:22');
INSERT INTO `role_auth` VALUES ('3', '2', '3', '1', '2017-09-09 13:44:00', '2017-09-09 15:47:20');
INSERT INTO `role_auth` VALUES ('4', '2', '4', '0', '2017-09-09 13:44:03', '2018-03-02 13:22:46');
INSERT INTO `role_auth` VALUES ('5', '3', '1', '0', '2017-09-09 13:44:06', '2018-03-02 13:22:46');
INSERT INTO `role_auth` VALUES ('6', '3', '2', '1', '2017-09-09 13:44:08', '2017-09-09 13:44:33');
INSERT INTO `role_auth` VALUES ('7', '3', '3', '1', '2017-09-09 13:44:11', '2017-09-13 09:59:00');
INSERT INTO `role_auth` VALUES ('8', '3', '4', '1', '2017-09-09 13:44:13', '2017-09-09 13:44:37');
INSERT INTO `role_auth` VALUES ('9', '3', '5', '1', '2017-09-09 13:44:16', '2017-09-09 13:44:41');
INSERT INTO `role_auth` VALUES ('26', '4', '1', '0', '2017-09-15 11:05:09', '2018-03-02 13:22:46');
INSERT INTO `role_auth` VALUES ('27', '4', '2', '1', '2017-09-15 11:05:11', '2017-09-15 11:05:47');
INSERT INTO `role_auth` VALUES ('28', '4', '3', '1', '2017-09-15 11:05:14', '2017-09-15 11:05:49');
INSERT INTO `role_auth` VALUES ('29', '4', '4', '1', '2017-09-15 11:05:17', '2017-09-15 11:05:52');
INSERT INTO `role_auth` VALUES ('30', '4', '5', '1', '2017-09-15 11:05:19', '2017-09-15 11:05:55');
INSERT INTO `role_auth` VALUES ('39', '4', '14', '1', '2017-09-15 11:05:43', '2017-09-15 11:06:18');
INSERT INTO `role_auth` VALUES ('40', '4', '15', '1', '2017-09-15 11:05:45', '2017-09-15 11:06:20');
INSERT INTO `role_auth` VALUES ('41', '4', '18', '1', '2017-09-15 11:05:47', '2017-09-16 11:09:09');
INSERT INTO `role_auth` VALUES ('42', '4', '19', '1', '2017-09-15 11:05:49', '2017-09-15 11:06:24');
INSERT INTO `role_auth` VALUES ('43', '4', '20', '1', '2017-09-15 11:05:52', '2017-09-22 13:19:28');
INSERT INTO `role_auth` VALUES ('48', '3', '26', '1', '2018-03-02 13:22:46', null);
INSERT INTO `role_auth` VALUES ('49', '3', '27', '1', '2018-03-02 13:22:46', null);
INSERT INTO `role_auth` VALUES ('50', '4', '26', '1', '2018-03-02 13:22:46', null);
INSERT INTO `role_auth` VALUES ('51', '4', '27', '1', '2018-03-02 13:22:46', null);

-- ----------------------------
-- Table structure for task_clock
-- ----------------------------
DROP TABLE IF EXISTS `task_clock`;
CREATE TABLE `task_clock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task` varchar(255) NOT NULL COMMENT '任务代码',
  `clock` time DEFAULT NULL COMMENT '时分秒',
  `enable` varchar(255) DEFAULT '1' COMMENT '是否可用 1-可用 0-不可用',
  PRIMARY KEY (`id`),
  KEY `index_task` (`task`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_clock
-- ----------------------------
INSERT INTO `task_clock` VALUES ('1', 'mailno_synchronize', '07:00:00', '1');
INSERT INTO `task_clock` VALUES ('2', 'mailno_synchronize', '21:00:00', '1');
INSERT INTO `task_clock` VALUES ('3', 'operate_status_by_route', '23:10:00', '1');

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_type` int(11) DEFAULT NULL,
  `auth_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `order_level` int(11) DEFAULT NULL,
  `parent_auth` int(11) DEFAULT NULL,
  `enable` int(11) NOT NULL DEFAULT '1',
  `sort` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('1', '1', '游客', '/**.html', null, null, '0', null, '2017-09-11 13:56:03', '2018-03-02 13:22:46');
INSERT INTO `user_auth` VALUES ('2', '1', '所有页面', '/html/**.html', null, null, '0', null, '2017-09-11 13:56:06', '2018-03-02 13:22:46');
INSERT INTO `user_auth` VALUES ('3', '1', '修改密码', '/auth/password', null, null, '0', null, '2017-09-11 13:56:09', '2018-03-02 13:22:46');
INSERT INTO `user_auth` VALUES ('4', '2', '新建快递单', '/sfExpress/order', null, null, '1', null, '2017-09-11 13:56:11', '2017-09-11 13:56:39');
INSERT INTO `user_auth` VALUES ('5', '3', '快递单打印', '/order/detail', null, null, '1', null, '2017-09-11 13:56:14', '2018-03-02 13:22:46');
INSERT INTO `user_auth` VALUES ('6', '4', '查看快递单列表', '/order/express', null, null, '1', null, '2017-09-11 13:56:16', '2017-09-11 13:56:44');
INSERT INTO `user_auth` VALUES ('7', '5', '快递单导出', '/order/expressExport', null, null, '1', null, '2017-09-11 13:56:19', '2017-09-11 13:56:46');
INSERT INTO `user_auth` VALUES ('16', '14', '查看角色', '/user/role', null, null, '1', null, '2017-09-11 13:56:42', '2017-09-11 13:57:09');
INSERT INTO `user_auth` VALUES ('17', '15', '查看,新增', '/user/user', null, null, '1', null, '2017-09-11 13:56:44', '2017-09-23 19:03:28');
INSERT INTO `user_auth` VALUES ('18', '18', '启用/禁用用户', '/user/userEnable', null, null, '1', null, '2017-09-11 13:56:47', '2017-09-11 13:57:14');
INSERT INTO `user_auth` VALUES ('19', '19', '用户角色配置', '/user/userRole', null, null, '1', null, '2017-09-11 13:56:49', '2017-09-11 13:57:17');
INSERT INTO `user_auth` VALUES ('20', '20', '重置用户密码', '/user/password', null, null, '1', null, '2017-09-22 09:19:03', '2017-09-22 09:21:07');
INSERT INTO `user_auth` VALUES ('25', '26', '查看物流信息', '/order/route', null, null, '1', null, '2018-03-02 13:22:46', null);
INSERT INTO `user_auth` VALUES ('26', '27', '快递单详情', '/order/detail', null, null, '1', null, '2018-03-02 13:22:46', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `enable` int(11) NOT NULL DEFAULT '1' COMMENT '1-可用 0-弃用',
  `enshow` int(11) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '游客', '0', '0', '2017-08-09 16:25:55', '2018-03-02 13:22:46');
INSERT INTO `user_role` VALUES ('2', '普通用户', '1', '1', '2017-08-29 14:29:08', '2017-08-30 20:29:21');
INSERT INTO `user_role` VALUES ('3', '系统管理员', '1', '1', '2017-08-29 14:31:11', '2017-08-30 20:29:22');
INSERT INTO `user_role` VALUES ('4', '超级管理员', '1', '1', '2017-09-15 11:01:39', '2017-09-15 11:02:18');

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL COMMENT '用户code',
  `role_id` int(11) DEFAULT NULL COMMENT '用户角色id(对应user_role表的id)',
  `enable` int(11) DEFAULT '1' COMMENT '1-可用 0-弃用',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES ('1', 'admin0000', '4', '1', '2017-08-30 19:53:21', '2017-09-15 16:34:52');

-- ----------------------------
-- Table structure for wxtb_user
-- ----------------------------
DROP TABLE IF EXISTS `wxtb_user`;
CREATE TABLE `wxtb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL COMMENT '账号',
  `hashed_password` varchar(255) DEFAULT NULL COMMENT '密码',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `enable` int(11) DEFAULT NULL COMMENT '0-不可用 1-可用',
  `account_non_expired` int(1) NOT NULL DEFAULT '1',
  `credentials_non_expired` int(1) NOT NULL DEFAULT '1',
  `account_non_locked` int(1) NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_account` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wxtb_user
-- ----------------------------
INSERT INTO `wxtb_user` VALUES ('1', 'admin0000', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '1', '1', '1', '1', '2017-08-29 16:17:54', '2018-05-18 15:25:53');
