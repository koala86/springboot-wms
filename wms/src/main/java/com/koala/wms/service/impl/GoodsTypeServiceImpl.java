package com.koala.wms.service.impl;

import com.koala.wms.entity.GoodsType;
import com.koala.wms.mapper.GoodsTypeMapper;
import com.koala.wms.service.IGoodsTypeService;
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
 * @since 2023-10-03
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public IPage<GoodsType> pageCustom(IPage<GoodsType> page, Wrapper<GoodsType> wrapper) {
        return goodsTypeMapper.pageCustom(page, wrapper);
    }

}
