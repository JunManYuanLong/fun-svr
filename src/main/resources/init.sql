CREATE TABLE `qa_case_available_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(8) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用例可用状态对照表-QA-FUN-20200709';

INSERT INTO `qa_case_available_status` (id,name) VALUES (1, '可用');
INSERT INTO `qa_case_available_status` (id,name) VALUES (2, '不可用');


CREATE TABLE `qa_case_collection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int(10) NOT NULL COMMENT '创建者ID',
  `envId` int(10) NOT NULL COMMENT '环境ID',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用例集名称',
  `pub` int(10) NOT NULL DEFAULT '0' COMMENT '共享状态',
  `editor` int(10) NOT NULL COMMENT '最后编辑者ID',
  `state` int(10) NOT NULL DEFAULT '1' COMMENT '最后运行状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name` (`envId`, `name`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '测试用例集-QA-FUN-20200709';


CREATE TABLE `qa_case_edit_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `caseId` int(10) NOT NULL COMMENT '用例ID',
  `editor` int(10) NOT NULL COMMENT '编辑者ID',
  `type` int(10) NOT NULL COMMENT '编辑类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用例编辑记录-QA-FUN-20200709';



CREATE TABLE `qa_case_edit_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用例编辑类型对照表-QA-FUN-20200709';

INSERT INTO `qa_case_edit_type` (id,name) VALUES (1, '创建用例');
INSERT INTO `qa_case_edit_type` (id,name)  VALUES (2, '修改用例属性');
INSERT INTO `qa_case_edit_type` (id,name)  VALUES (3, '修改用例数据');


CREATE TABLE `qa_case_project_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `caseId` int(10) NOT NULL COMMENT '用例ID',
  `projectId` int(10) NOT NULL COMMENT '项目ID',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_only` (`caseId`,`projectId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用例和项目关联表-QA-FUN-20200709';



CREATE TABLE `qa_case_run_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `runId` int(10) NOT NULL COMMENT '用例集运行关联ID',
  `mark` int(10) NOT NULL COMMENT '运行标记',
  `uid` int(10) NOT NULL COMMENT '运行者ID',
  `caseId` int(10) NOT NULL COMMENT '用例ID',
  `code` int(10) NOT NULL COMMENT '响应业务code',
  `result` int(10) NOT NULL COMMENT '测试结果',
  `verify` text  NOT NULL COMMENT '验证结果',
  `params` text  NOT NULL COMMENT '请求参数',
  `headers` text  NOT NULL COMMENT '请求header',
  `response` text  NOT NULL COMMENT '响应体',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用例运行记录表-QA-FUN-20200709';



CREATE TABLE `qa_case_run_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用例运行状态对照表-QA-FUN-20200709';

INSERT INTO `qa_case_run_status` (id,name) VALUES (2, '成功');
INSERT INTO `qa_case_run_status` (id,name) VALUES (3, '失败');
INSERT INTO `qa_case_run_status` (id,name) VALUES (4, '无法运行');
INSERT INTO `qa_case_run_status` (id,name) VALUES (5, '用户错误');



CREATE TABLE `qa_collection_case_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `collectionId` int(10) NOT NULL COMMENT '用例集ID',
  `caseId` int(10) NOT NULL COMMENT '用例ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_relation` (`collectionId`,`caseId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用例集于测试用例关系列表-QA-FUN-20200709';



CREATE TABLE `qa_collection_edit_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `collectionId` int(10) NOT NULL COMMENT '用例集ID',
  `editor` int(10) NOT NULL COMMENT '编辑者ID',
  `type` int(10) NOT NULL COMMENT '编辑类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用例集编辑记录-QA-范凯强-20200709';


CREATE TABLE `qa_collection_edit_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用例集编辑类型对照表-QA-FUN-20200709';

INSERT INTO `qa_collection_edit_type` (id,name) VALUES (1, '创建用例集');
INSERT INTO `qa_collection_edit_type` (id,name) VALUES (2, '添加测试用例');
INSERT INTO `qa_collection_edit_type` (id,name) VALUES (3, '删除测试用例');
INSERT INTO `qa_collection_edit_type` (id,name) VALUES (4, '编辑用例集名称');
INSERT INTO `qa_collection_edit_type` (id,name) VALUES (5, '共享测试用例集');




CREATE TABLE `qa_collection_pub_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用例集共享状态对照表-QA-FUN-20200709';


INSERT INTO `qa_collection_pub_status` (id,name) VALUES (1, '已共享');
INSERT INTO `qa_collection_pub_status` (id,name) VALUES (2, '未共享');




CREATE TABLE `qa_collection_run_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `runId` int(10) NOT NULL COMMENT '用例集运行ID',
  `collectionId` int(10) NOT NULL COMMENT '用例集ID',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '运行名称',
  `envId` int(10) NOT NULL COMMENT '环境ID',
  `caseNum` int(10) NOT NULL COMMENT '用例数量',
  `result` int(10) NOT NULL COMMENT '运行结果',
  `success` int(10) NOT NULL COMMENT '成功数量',
  `fail` int(10) NOT NULL COMMENT '失败数量',
  `unrun` int(10) NOT NULL COMMENT '无法运行数量',
  `userError` int(10) NOT NULL COMMENT '用户错误数量',
  `startTime` datetime NOT NULL COMMENT '开始时间',
  `endTime` datetime NOT NULL COMMENT '结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用例集运行记录表-QA-FUN-20200709';




CREATE TABLE `qa_collection_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(8) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用例集运行状态对照表-QA-FUN-20200709';



INSERT INTO `qa_collection_status` (id,name) VALUES (1, '未测试');
INSERT INTO `qa_collection_status` (id,name) VALUES (2, '成功');
INSERT INTO `qa_collection_status` (id,name) VALUES (3, '失败');


CREATE TABLE `qa_pub_data` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` int(10) NOT NULL COMMENT '创建者ID',
  `envId` int(10) NOT NULL COMMENT '环境ID',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `list` text NOT NULL COMMENT '公共数据',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户公共数据表-QA-FUN-20200709';




CREATE TABLE `qa_request_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `requestid` varchar(24) NOT NULL COMMENT '请求标记id',
  `mark` int(10) NOT NULL COMMENT '测试用例关联ID',
  `host` varchar(100) NOT NULL COMMENT '域名',
  `api` varchar(100) NOT NULL COMMENT '接口',
  `method` varchar(5) NOT NULL COMMENT '方法',
  `state` int(10) NOT NULL COMMENT 'HTTPcode',
  `code` int(10) NOT NULL COMMENT '业务code',
  `cost` int(10) NOT NULL COMMENT '响应消耗时间',
  `size` int(10) NOT NULL COMMENT '响应体大小',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='所有HTTP请求记录表-QA-FUN-20200709';


CREATE TABLE `qa_role_name` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用户身份对照表-QA-FUN-20200709';


INSERT INTO `qa_role_name` (id,name) VALUES (1, '学生pad');
INSERT INTO `qa_role_name` (id,name) VALUES (2, '学生空间');
INSERT INTO `qa_role_name` (id,name) VALUES (3, '老师pad');
INSERT INTO `qa_role_name` (id,name) VALUES (4, '教师空间');
INSERT INTO `qa_role_name` (id,name) VALUES (5, '公立校');
INSERT INTO `qa_role_name` (id,name) VALUES (6, 'OK学生');


CREATE TABLE `qa_test_case` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` int(10) NOT NULL COMMENT '创建者ID',
  `envId` int(10) NOT NULL COMMENT '环境ID',
  `projectList` varchar(100) NOT NULL COMMENT '关联项目ID',
  `serviceId` int(10) NOT NULL COMMENT '服务ID',
  `moduleId` int(10) NOT NULL COMMENT '模块ID',
  `apiId` int(10) NOT NULL COMMENT '接口ID',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '接口地址',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '用例名',
  `type` varchar(1) NOT NULL DEFAULT '' COMMENT '读写类型',
  `editor` int(10) NOT NULL COMMENT '最后编辑人',
  `method` varchar(10) NOT NULL DEFAULT '' COMMENT '请求方式',
  `headers` text  NOT NULL COMMENT '请求header',
  `params` text  NOT NULL COMMENT '请求参数',
  `leve` int(10) NOT NULL COMMENT '用例等级',
  `verify` text NOT NULL COMMENT '测试期望',
  `available` int(10) NOT NULL DEFAULT '2' COMMENT '可用与否',
  `headersmoco` text NOT NULL COMMENT 'header moco数据',
  `paramsmoco` text NOT NULL COMMENT '参数 moco数据',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name` (`envId`,`apiId`,`name`,`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用例表-QA-FUN-20200709';




CREATE TABLE `qa_test_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` int(10) NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `envId` int(10) NOT NULL DEFAULT '0' COMMENT '环境ID',
  `usr` varchar(11) NOT NULL COMMENT '用户名',
  `pwd` varchar(20) NOT NULL COMMENT '明文密码',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `roleId` int(10) NOT NULL COMMENT '身份ID',
  `state` int(10) NOT NULL DEFAULT '0' COMMENT '状态',
  `des` varchar(128) NOT NULL COMMENT '描述',
  `certificate` text NOT NULL COMMENT '登录凭证',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_only` (`envId`,`usr`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用户表-QA-FUN-20200709';


CREATE TABLE `qa_user_status_name` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户身份id、name对照表-QA-FUN-20200709';

INSERT INTO `qa_user_status_name` (id,name) VALUES (1, '默认');
INSERT INTO `qa_user_status_name` (id,name) VALUES (2, '可用');
INSERT INTO `qa_user_status_name` (id,name) VALUES (3, '不可用');
INSERT INTO `qa_user_status_name` (id,name) VALUES (4, '无法验证');

CREATE TABLE `qa_lock` (
`id` bigint(20) unsigned NOT NULL COMMENT '锁key',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分布式锁表-QA-范凯强-20200715';

