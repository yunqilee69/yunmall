package com.yunqi.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yunqi.common.model.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_sys_user")
public class User extends BaseEntity {

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("nickname")
    private String nickname;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("is_enable")
    private Boolean enable ;
}
