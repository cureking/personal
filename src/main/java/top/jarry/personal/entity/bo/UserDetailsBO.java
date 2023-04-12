package top.jarry.personal.entity.bo;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.jarry.personal.entity.User;

import java.util.Collection;

/**
 * @Description 用户明细模型，用于springSecurity用户承载模型
 * @Date 2023/3/2 01:12
 * @Author king
 */
public class UserDetailsBO extends User implements UserDetails {


    public UserDetailsBO(User user) {
        BeanUtils.copyProperties(user, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(new UserGrantedAuthority(super.getUserAuthorization().getRoleName()));
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private class UserGrantedAuthority implements GrantedAuthority {

        private String authority;

        public UserGrantedAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
    }
}
