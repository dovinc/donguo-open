-- --------------------------------------------------------
-- 主机:                           47.98.119.177
-- 服务器版本:                        5.7.22 - Source distribution
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 open_course 的数据库结构
CREATE DATABASE IF NOT EXISTS `open_course` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `open_course`;

-- 导出  表 open_course.sys_attr 结构
CREATE TABLE IF NOT EXISTS `sys_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性表主键id',
  `dept_id` int(11) DEFAULT '0' COMMENT '所属部门的id，默认为0所有部门',
  `name` varchar(50) DEFAULT NULL COMMENT '属性名',
  `value` varchar(50) DEFAULT NULL COMMENT '属性值',
  `description` varchar(50) DEFAULT NULL COMMENT '属性描述',
  `multistage` int(11) NOT NULL DEFAULT '0' COMMENT '是否多级，级联的0表示否',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '属性添加时间',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统属性表';

-- 正在导出表  open_course.sys_attr 的数据：~0 rows (大约)
DELETE FROM `sys_attr`;
/*!40000 ALTER TABLE `sys_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_attr` ENABLE KEYS */;

-- 导出  表 open_course.sys_attr_value 结构
CREATE TABLE IF NOT EXISTS `sys_attr_value` (
  `attr_value_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性值表主键',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属部门的id，默认值为0 所有部门',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级id 若无父级则为0',
  `code` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT 'view层显示值',
  `type_name` varchar(50) NOT NULL COMMENT '属性值的类型',
  `type_code` varchar(50) NOT NULL COMMENT '属性值类型编码对应属性表中的编码',
  `value` varchar(50) NOT NULL COMMENT '存库属性值',
  `description` varchar(50) NOT NULL COMMENT '描述',
  `remark` varchar(50) NOT NULL COMMENT '备注',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`attr_value_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性键表attr表  所对应的属性值表';

-- 正在导出表  open_course.sys_attr_value 的数据：~0 rows (大约)
DELETE FROM `sys_attr_value`;
/*!40000 ALTER TABLE `sys_attr_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_attr_value` ENABLE KEYS */;

-- 导出  表 open_course.sys_dept 结构
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- 正在导出表  open_course.sys_dept 的数据：~0 rows (大约)
DELETE FROM `sys_dept`;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 open_course.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，菜单Id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0L',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(500) DEFAULT NULL COMMENT '权限（Spring Security 对应的权限字符串/若为Shiro 则也为其对应），以'',''分隔（业务逻辑中是使用'',''拆分的）',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序字段',
  `path` varchar(50) DEFAULT NULL COMMENT 'path/name 的唯一标识',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- 正在导出表  open_course.sys_menu 的数据：~16 rows (大约)
DELETE FROM `sys_menu`;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `permissions`, `type`, `icon`, `order_num`, `path`) VALUES
	(1, 0, 'nested', NULL, NULL, 0, 'tree', 0, 'nested'),
	(2, 1, 'menu1', 'nested/menu1/index', NULL, 0, 'menu', 3, 'menu1'),
	(3, 1, 'Menu2', 'nested/menu2/index', NULL, 1, '', NULL, 'menu2'),
	(4, 2, 'menu1-1', 'nested/menu1/menu1-1/index', NULL, 1, NULL, NULL, 'menu1-1'),
	(5, 2, 'menu1-2', 'nested/menu1/menu1-2/index', NULL, 0, NULL, NULL, 'menu1-2'),
	(6, 2, 'menu1-3', 'nested/menu1/menu1-3/index', NULL, 1, '', NULL, 'menu1-3'),
	(7, 5, 'menu1-2-1', 'nested/menu1/menu1-2/menu1-2-1/index', NULL, 1, NULL, NULL, 'menu1-2-1'),
	(8, 5, 'menu1-2-2', 'nested/menu1/menu1-2/menu1-2-2/index', NULL, 1, NULL, NULL, 'menu1-2-2'),
	(9, 0, '系统管理', NULL, NULL, 0, 'list', 1, 'sys'),
	(10, 9, '菜单管理', 'module/sys/menu/index', NULL, 1, 'list', 2, 'sys-menu'),
	(11, 9, '用户管理', 'module/sys/user/index', NULL, 1, 'peoples', 3, 'sys-user'),
	(49, 10, '查看', '', '', 2, '', 0, ''),
	(50, 10, '修改', '', '', 2, '', 0, ''),
	(51, 10, '删除', '', '', 2, '', 0, ''),
	(52, 9, '角色管理', 'module/sys/role/index', '', 1, 'people', 0, 'sys-role'),
	(53, 0, '测试删除', '/test', 'sss', 1, 'skill', 0, '/test');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 open_course.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色';

-- 正在导出表  open_course.sys_role 的数据：~3 rows (大约)
DELETE FROM `sys_role`;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`role_id`, `role_name`, `description`, `create_user_id`, `create_time`) VALUES
	(1, '目标角色', '测试使用目标角色', 1, '2019-11-17 20:47:14'),
	(2, '超级管理员', 'superManager', 1, '2019-11-16 14:40:12');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 open_course.sys_role_dept 结构
CREATE TABLE IF NOT EXISTS `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- 正在导出表  open_course.sys_role_dept 的数据：~0 rows (大约)
DELETE FROM `sys_role_dept`;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;

-- 导出  表 open_course.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- 正在导出表  open_course.sys_role_menu 的数据：~9 rows (大约)
DELETE FROM `sys_role_menu`;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES
	(133, 2, 10),
	(134, 2, 49),
	(135, 2, 50),
	(136, 2, 51),
	(137, 2, 11),
	(138, 2, 9),
	(139, 1, 51),
	(140, 1, 9),
	(141, 1, 10);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 open_course.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) DEFAULT NULL COMMENT '加密后的密码',
  `salt` varchar(255) NOT NULL COMMENT '盐',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0 停用 1 启用',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL COMMENT '更新id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_unique` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 正在导出表  open_course.sys_user 的数据：2 rows
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `username`, `email`, `mobile`, `password`, `salt`, `status`, `create_user_id`, `create_time`, `update_user_id`, `update_time`) VALUES
	(1, 'admin', 'root@renren.io', '13612345678', 'f86b5640ffb3e293d313bf9730fe906b', 'YzcmCZNvbXocrsz9dm8e', 1, 1, '2016-11-11 11:11:11', 0, NULL),
	(12, 'dongguo', 'dongguozc@163.com', '13572977777', '500237309f4aa7cb709c63100b2e5e82', 'fsdgxayMGNtIDsddguYS', 1, 12, '2019-11-10 15:09:11', 0, NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 open_course.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 正在导出表  open_course.sys_user_role 的数据：~0 rows (大约)
DELETE FROM `sys_user_role`;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 open_course.sys_user_token 结构
CREATE TABLE IF NOT EXISTS `sys_user_token` (
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `token` varchar(100) NOT NULL COMMENT 'token字符串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `expired_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- 正在导出表  open_course.sys_user_token 的数据：~2 rows (大约)
DELETE FROM `sys_user_token`;
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` (`user_id`, `username`, `token`, `create_time`, `expired_time`) VALUES
	(1, 'admin', '3d36f68f7323e1706dfbb477479f6181', '2019-11-27 21:58:41', '2019-11-28 21:58:41'),
	(12, 'dongguo', '1d9a0d6047e40d8c5a8d76754e34f07c', '2019-11-26 11:42:32', '2019-11-27 16:54:17');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
