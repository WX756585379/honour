DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `account` varchar(20) NOT NULL COMMENT '登录账户',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户名，昵称',
  `real_name` varchar(50) DEFAULT NULL COMMENT '用户真实姓名',
  `gender` int(1) NOT NULL DEFAULT '0' COMMENT '性别：0-未知，1-男，2-女',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `open_id` varchar(64) NOT NULL DEFAULT '' COMMENT '微信登录openid',
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `login_time` datetime DEFAULT NULL COMMENT '记录登录时间',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `phone` varchar(64) NOT NULL COMMENT '电话号码',
  `login_ip` varchar(64) NOT NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `status` char(3) NOT NULL DEFAULT 'EBL' COMMENT 'EBL-有效，DEL-删除，LCK-锁定',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`pid`),
  UNIQUE KEY `UNIQUE_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';


DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `name` varchar(64) NOT NULL COMMENT '角色',
  `name_zh` varchar(64) NOT NULL COMMENT '角色名称',
  `status` char(3) NOT NULL DEFAULT 'EBL' COMMENT 'EBL-有效，DEL-删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';


DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `user_id` bigint(20) NOT NULL COMMENT '关联user',
  `role_id` bigint(20) NOT NULL COMMENT '关联role',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';


DROP TABLE IF EXISTS `m_menu`;
CREATE TABLE `m_menu` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `url` varchar(64) NOT NULL COMMENT '用户密码，MD5加密',
  `path` varchar(64) NOT NULL COMMENT '登录账户',
  `component` varchar(64) DEFAULT NULL COMMENT '对应Vue组件名称',
  `name` varchar(64) DEFAULT NULL COMMENT '对应Vue组件名称',
  `icon_cls` varchar(64) DEFAULT NULL COMMENT '对应图标样式',
  `keep_alive` int(1) DEFAULT NULL COMMENT '对应前端',
  `require_auth` int(1) DEFAULT NULL COMMENT '对应前端',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级菜单',
  `status` char(3) NOT NULL DEFAULT 'EBL' COMMENT 'EBL-有效，DEL-删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='左侧菜单';


DROP TABLE IF EXISTS `m_menu_role`;
CREATE TABLE `m_menu_role` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `role_id` bigint(20) NOT NULL COMMENT '关联role',
  `menu_id` bigint(20) NOT NULL COMMENT '关联menu',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';




