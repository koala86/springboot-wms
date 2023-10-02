package com.koala.wms.service;

import com.koala.wms.entity.Storage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author koala
 * @since 2023-10-02
 */
public interface IStorageService extends IService<Storage> {
    IPage<Storage> pageCustom(IPage<Storage> page, Wrapper<Storage> wrapper);
}
