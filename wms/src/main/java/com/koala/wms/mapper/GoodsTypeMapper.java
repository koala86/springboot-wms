package com.koala.wms.mapper;

import com.koala.wms.entity.GoodsType;
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
 * @since 2023-10-03
 */
@Mapper
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    IPage<GoodsType> pageCustom(IPage<GoodsType> page, @Param(Constants.WRAPPER) Wrapper<GoodsType> wrapper);
}
