# 用户表，包含管理员
drop table if exists tb_sys_user;
create table tb_sys_user
(
    id         bigint primary key not null comment '主键',
    update_by  bigint             not null comment '更新人',
    update_at  datetime           not null comment '更新时间',
    create_by  bigint             not null comment '创建人',
    create_at  datetime           not null comment '创建时间',
    username   varchar(64) comment '账号',
    password   varchar(64) comment '密码',
    nickname   varchar(64) comment '昵称',
    avatar     varchar(512) comment '头像',
    phone      varchar(64) comment '手机号',
    email      varchar(64) comment '邮箱',
    is_enabled tinyint(1) comment '是否启用：1->启用，0->禁用',
    is_deleted tinyint(1) comment '是否删除：1->删除，0->未删除'
);

# 角色表
drop table if exists tb_sys_role;
create table tb_sys_role
(
    id         bigint primary key not null comment '主键',
    update_by  bigint             not null comment '更新人',
    update_at  datetime           not null comment '更新时间',
    create_by  bigint             not null comment '创建人',
    create_at  datetime           not null comment '创建时间',
    name       varchar(64) comment '名称',
    auth_key   varchar(64) comment '角色权限',
    remark     varchar(64) comment '备注',
    is_enable  tinyint(1) comment '是否启用：1->启用，0->禁用',
    is_deleted tinyint(1) comment '是否删除：1->删除，0->未删除'
);

# 用户角色关联表
drop table if exists tb_sys_user_role;
create table tb_sys_user_role
(
    id      bigint primary key not null comment '主键',
    user_id bigint             not null comment '用户表主键',
    role_id bigint             not null comment '角色表主键'
);

# 角色菜单表
drop table if exists tb_sys_role_menu;
create table tb_sys_role_menu
(
    id      bigint primary key not null comment '主键',
    role_id bigint             not null comment '角色表主键',
    menu_id bigint             not null comment '菜单表主键'
);

# 菜单表
create table tb_sys_menu
(
    id         bigint primary key not null comment '主键',
    update_by  bigint             not null comment '更新人',
    update_at  datetime           not null comment '更新时间',
    create_by  bigint             not null comment '创建人',
    create_at  datetime           not null comment '创建时间',
    parent_id  bigint comment '父菜单',
    name       varchar(64) comment '路由名称（唯一）',
    title      varchar(64) comment '路由标题',
    path       varchar(64) comment '路由路径/内嵌网页路径/外链路径',
    redirect   varchar(64) comment '重定向路径',
    component  varchar(64) comment 'vue文件路径',
    icon       varchar(64) comment '菜单图标',
    sequence   int comment '序号',
    is_visible tinyint(1) comment '是否显示该菜单',
    type       varchar(16) comment '类型：menu->菜单,iframe->内嵌网页,link->外链,button->按钮',
    auth_key   varchar(64) comment '权限值'
);

# 数据字典
drop table if exists tb_sys_dict;
create table tb_sys_dict
(
    id        bigint primary key not null comment '主键',
    update_by bigint             not null comment '更新人',
    update_at datetime           not null comment '更新时间',
    create_by bigint             not null comment '创建人',
    create_at datetime           not null comment '创建时间',
    name      varchar(64),
    code      varchar(64)
);

# 字典项
drop table if exists tb_sys_dict_option;
create table tb_sys_dict_option
(
    id        bigint primary key not null comment '主键',
    update_by bigint             not null comment '更新人',
    update_at datetime           not null comment '更新时间',
    create_by bigint             not null comment '创建人',
    create_at datetime           not null comment '创建时间',
    dict_id   bigint,
    dict_code varchar(64),
    label     varchar(64),
    value     varchar(64)
);


