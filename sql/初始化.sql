# 用户表，包含管理员和普通用户
create table tb_sys_user
(
    id        bigint primary key not null comment '主键',
    update_by bigint             not null comment '更新人',
    update_at datetime           not null comment '更新时间',
    create_by bigint             not null comment '创建人',
    create_at datetime           not null comment '创建时间',
    username  varchar(64) comment '账号',
    password  varchar(64) comment '密码',
    nickname  varchar(64) comment '昵称',
    avatar    varchar(512) comment '头像',
    phone     varchar(64) comment '手机号',
    email     varchar(64) comment '邮箱',
    is_enable tinyint(1) comment '是否启用：1->启用，0->禁用'
);

# 角色表
create table tb_sys_role
(
    id        bigint primary key not null comment '主键',
    update_by bigint             not null comment '更新人',
    update_at datetime           not null comment '更新时间',
    create_by bigint             not null comment '创建人',
    create_at datetime           not null comment '创建时间',
    name      varchar(64) comment '名称',
    auth_key  varchar(64) comment '权限值',
    is_enable tinyint(1) comment '是否启用：1->启用，0->禁用'
);

# 用户角色关联表
create table tb_sys_user_role
(
    id        bigint primary key not null comment '主键',
    user_id        bigint not null comment '用户表主键',
    role_id        bigint not null comment '角色表主键'
);

# 角色菜单表
create table tb_sys_role_menu(
                                 id        bigint primary key not null comment '主键',
                                 role_id        bigint not null comment '角色表主键',
                                 menu_id        bigint not null comment '菜单表主键'
);

# 菜单表(访问权限)
create table tb_sys_menu
(
    id        bigint primary key not null comment '主键',
    update_by bigint             not null comment '更新人',
    update_at datetime           not null comment '更新时间',
    create_by bigint             not null comment '创建人',
    create_at datetime           not null comment '创建时间',
    parent_id bigint comment '父菜单',
    name varchar(64) comment '名称',
    icon varchar(64) comment '图标',
    path varchar(64) comment '路由导航',
    component varchar(64) comment '前端组件',
    sequence varchar(64) comment '排序',
    type varchar(64) comment '类型：directory->目录,item->菜单项,button->按钮',
    auth_key varchar(64) comment '权限值',
    is_visible tinyint(1) comment '是否显示：1->显示,0->隐藏',
    is_disabled tinyint(1) comment '是否禁用:1->禁用,0->启用'
);


# 数据字典
create table tb_sys_dict
(
    id        bigint primary key,
    name      varchar(64),
    code      varchar(64),
    update_by datetime not null,
    update_at datetime not null,
    create_by bigint   not null,
    create_at datetime not null
);

# 字典项
create table tb_sys_dict_option
(
    id        bigint primary key,
    dict_id   bigint,
    dict_code varchar(64),
    label     varchar(64),
    value     varchar(64),
    update_by datetime not null,
    update_at datetime not null,
    create_by bigint   not null,
    create_at datetime not null
);
