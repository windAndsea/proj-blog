package com.bandit.blog.system.service.impl;

import com.bandit.blog.entities.SysUser;
import com.bandit.blog.system.mapper.SysUserMapper;
import com.bandit.blog.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
