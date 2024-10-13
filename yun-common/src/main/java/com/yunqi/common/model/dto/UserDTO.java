package com.yunqi.common.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户类，包含具体的权限
 */
@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    // 是否为管理员
    private Boolean admin;

    // 角色
    private List<String> roles;

    // 权限key
    private List<String>  permissions;

}
