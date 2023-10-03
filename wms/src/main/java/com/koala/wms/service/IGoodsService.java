package com.koala.wms.service;

import com.koala.wms.entity.Goods;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author koala
 * @since 2023-10-03
 */
public interface IGoodsService extends IService<Goods> {
    IPage<Goods> pageCustom(IPage<Goods> page, Wrapper<Goods> wrapper);
}
