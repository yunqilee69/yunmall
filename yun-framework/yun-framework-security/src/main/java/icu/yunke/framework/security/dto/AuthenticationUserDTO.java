package icu.yunke.framework.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class") //
@JsonIgnoreProperties(ignoreUnknown = true) // 在序列化时，忽略不知道的属性
public class AuthenticationUserDTO implements UserDetails, Serializable {

    private static final long serialVersionUID = 8311215151102456070L;

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

    /**
     * 不使用spring security提供的权限校验注解，使用自定义的权限校验注解
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 检查账号是否过期--不使用，返回true
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 检查账号是否锁定--不使用，返回true
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 检查凭证（密码）是否过期--不使用，返回true
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 检查账号是否启用--使用，根据enabled字段
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
