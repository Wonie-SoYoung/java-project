package com.soyoung.security.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * security的用户详情类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityUserDetails implements UserDetails {

    // openid
    private String openid;

    // 用户状态，0表示有效用户，1表示无效用户
    private String state;

    // 用户的权限集合
    private Collection<? extends GrantedAuthority> authorties;

    public SecurityUserDetails(String openid) {
        this.openid = openid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorties;
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        return openid;
    }


    /**
     * 指示用户的帐户是否已过期。过期的帐户无法通过身份验证。
     *
     * @return true如果用户的帐户有效（即未过期）， false如果不再有效（即已过期）
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 指示用户是锁定还是解锁。锁定的用户无法进行身份验证。
     *
     * @return true是未锁定，false是已锁定
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * 指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证
     *
     * @return true如果用户的凭证有效（即未过期）， false如果不再有效（即已过期）
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 指示用户是启用还是禁用。禁用的用户无法进行身份验证。
     *
     * @return true用户已启用，false用户已经禁用
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return "0".equals(state);
    }
}
