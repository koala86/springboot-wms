package com.koala.wms.mapper;

import com.koala.wms.entity.Goods;
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
public interface GoodsMapper extends BaseMapper<Goods> {
    IPage<Goods> pageCustom(IPage<Goods> page, @Param(Constants.WRAPPER) Wrapper<Goods> wrapper);
}
