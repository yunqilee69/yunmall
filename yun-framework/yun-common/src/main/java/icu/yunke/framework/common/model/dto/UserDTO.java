package icu.yunke.framework.common.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户类，包含具体的权限
 */
@Data
public class UserDTO {

    // 主键
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 角色key
    private List<String> roles;

    // 权限key
    private List<String>  perms;

}
