package com.koala.wms.service.impl;

import com.koala.wms.entity.User;
import com.koala.wms.mapper.UserMapper;
import com.koala.wms.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author koala
 * @since 2023-08-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> pageCustom(IPage<User> page, Wrapper<User> wrapper) {
        return userMapper.pageCustom(page, wrapper);
    }

}
