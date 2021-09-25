CREATE TABLE `cfg_global` (
  `system_name` varchar(32) NOT NULL COMMENT '系统名称',
  `group_name` varchar(32) NOT NULL COMMENT '分组名称',
  `config_key` varchar(32) NOT NULL COMMENT '配置标识',
  `config_value` varchar(1024) NOT NULL COMMENT '配置内容',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_valid` int NOT NULL DEFAULT '1' COMMENT '记录有效状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局配置表';

CREATE TABLE `exception_board` (
  `system_name` varchar(32) NOT NULL COMMENT '系统名称',
  `biz_type` varchar(32) NOT NULL COMMENT '业务类型',
  `process_state` varchar(32) NOT NULL COMMENT '处理状态',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `trace_id` varchar(64) NOT NULL COMMENT '链路追踪ID',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_valid` int NOT NULL DEFAULT '1' COMMENT '记录有效状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常看板表';