package com.koala.wms.service.impl;

import com.koala.wms.entity.Goods;
import com.koala.wms.mapper.GoodsMapper;
import com.koala.wms.service.IGoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public IPage<Goods> pageCustom(IPage<Goods> page, Wrapper<Goods> wrapper) {
        return goodsMapper.pageCustom(page, wrapper);
    }

    
}
