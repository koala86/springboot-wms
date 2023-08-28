package com.koala.wms.service;

import com.koala.wms.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author koala
 * @since 2023-08-25
 */
public interface IUserService extends IService<User> {
    IPage<User> pageCustom(IPage<User> page, Wrapper<User> wrapper);
}
