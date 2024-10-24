package icu.yunke.framework.common.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户类，包含具体的权限
 */
@Data
public class UserDTO {

    // 用户id
    private Long id;

    // 创建时间
    private LocalDateTime createAt;

    // 创建人
    private Long createBy;

    // 更新时间
    private LocalDateTime updateAt;

    // 更新人
    private Long updateBy;

    // 登录用户名
    private String username;

    // 登录密码
    private String password;

    // 昵称
    private String nickname;

    // 头像
    private String avatar;

    // 手机号
    private String phone;

    // 邮箱
    private String email;

    // 是否启用
    private Boolean enabled;

    // 是否删除：是-1，否-0
    private Boolean deleted;

    // 该用户拥有的权限集合
    private List<String> perms;

    // 该用户所拥有的角色集合
    private List<String> roles;

}
