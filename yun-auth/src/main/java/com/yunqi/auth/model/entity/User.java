package com.yunqi.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yunqi.common.model.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_sys_user")
public class User extends BaseEntity {

    /**
     * 账号
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 是否启用
     */
    @TableField("is_enable")
    private Boolean enable ;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean deleted;
}
