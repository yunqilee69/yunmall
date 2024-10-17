package icu.yunke.framework.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationUserDTO implements UserDetails {

    // 用户id
    private Long userId;

    // 登录用户名
    private String username;

    // 登录密码
    private String password;

    // 是否启用
    private Boolean enabled;

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
     * 检查账号是否启用--使用，根据enable字段
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
