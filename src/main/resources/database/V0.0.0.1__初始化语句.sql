-- 用户信息表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id              bigint(20)                      NOT NULL AUTO_INCREMENT COMMENT '主键id',
  username        varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  secret_code     varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  avatar          varchar(255) COLLATE utf8mb4_bin         DEFAULT NULL COMMENT '头像信息',
  email           varchar(64) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '邮箱',
  telephone       varchar(16) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '用户手机号',
  last_login_time timestamp                       NULL     DEFAULT NULL COMMENT '最后一次登录时间',
  remark          varchar(255) COLLATE utf8mb4_bin         DEFAULT NULL COMMENT '备注',
  use_flag        int(2)                          NOT NULL DEFAULT '1' COMMENT '用户状态 1正常 0锁定',
  insert_time     timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  update_time     timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- 日志记录表
DROP TABLE IF EXISTS sys_blog;
CREATE TABLE sys_blog
(
  id          bigint(20)                       NOT NULL AUTO_INCREMENT COMMENT '主键id',
  user_id     bigint(20)                       NOT NULL COMMENT '用户id',
  title       varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  description varchar(255) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '描述',
  content     longtext COLLATE utf8mb4_bin COMMENT '内容',
  remark      varchar(255) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '备注',
  use_flag    int(2)                           NOT NULL DEFAULT '1' COMMENT '1有效 0无效',
  insert_time timestamp                        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  update_time timestamp                        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- 数据字典表
DROP TABLE IF EXISTS sys_data_dictionary;
CREATE TABLE sys_data_dictionary
(
  id          int(11)                                         NOT NULL AUTO_INCREMENT COMMENT '主键id',
  data_type   varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类型',
  code        varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '编码',
  value       varchar(200) CHARACTER SET utf8 COLLATE utf8_bin         DEFAULT '' COMMENT '值',
  is_config   int(2)                                                   DEFAULT '0' COMMENT '是否选中，0否，1是',
  is_check    int(2)                                                   DEFAULT '0' COMMENT '是否设置默认，0不设置，1设置默认',
  parent_id   int(11)                                                  DEFAULT '0' COMMENT '上级id',
  remark      varchar(200) CHARACTER SET utf8 COLLATE utf8_bin         DEFAULT '' COMMENT '字典备注',
  use_flag    int(2) unsigned                                 NOT NULL DEFAULT '1' COMMENT '是否启用，0不启用，1启用',
  insert_time timestamp                                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time timestamp                                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC COMMENT ='数据字典表';
