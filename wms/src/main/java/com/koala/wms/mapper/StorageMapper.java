package com.koala.wms.mapper;

import com.koala.wms.entity.Storage;
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
 * @since 2023-10-02
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
    IPage<Storage> pageCustom(IPage<Storage> page, @Param(Constants.WRAPPER) Wrapper<Storage> wrapper);
}
