package com.bandit.blog.system.feign;

import com.bandit.blog.entities.SysMenu;
import com.bandit.blog.entities.SysUser;
import com.bandit.blog.feign.IFeignSystemController;
import com.bandit.blog.system.service.ISysMenuService;
import com.bandit.blog.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "系统微服务Feign接口")
@RestController
public class FeignSystemController implements IFeignSystemController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserService.findByUsername(username);
    }

    @Override
    public List<SysMenu> findMenuByUserId(String userId) {
        return sysMenuService.findMenuByUserId(userId);
    }
}
