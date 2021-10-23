package com.bandit.blog.oauth2.service.impl;

import com.bandit.blog.entities.SysMenu;
import com.bandit.blog.entities.SysUser;
import com.bandit.blog.feign.IFeignSystemController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IFeignSystemController feignSystemController;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("username cannot be empty");
        }
        SysUser sysUser = feignSystemController.findUserByUsername(username);

        if (sysUser == null) {
            throw new BadCredentialsException("the user is not exist");
        }

        List<SysMenu> sysMenuList = feignSystemController.findMenuByUserId(sysUser.getId());

        List<GrantedAuthority> grantedAuthorities = null;
        if (CollectionUtils.isNotEmpty(sysMenuList)) {
            grantedAuthorities = new ArrayList<>();
            for (SysMenu menu : sysMenuList) {
                String code = menu.getCode(); // 权限标识
                grantedAuthorities.add(new SimpleGrantedAuthority(code));
            }
        }

        JwtUser jwtUser = new JwtUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(),
                sysUser.getNickName(), sysUser.getImageUrl(), sysUser.getMobile(), sysUser.getEmail(),
                sysUser.getIsAccountNonExpired(), sysUser.getIsAccountNonLocked(),
                sysUser.getIsCredentialsNonExpired(), sysUser.getIsEnabled(),
                grantedAuthorities);

        return jwtUser;
    }
}
