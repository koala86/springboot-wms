package com.koala.wms.mapper;

import com.koala.wms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author koala
 * @since 2023-08-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    IPage<User> pageCustom(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
