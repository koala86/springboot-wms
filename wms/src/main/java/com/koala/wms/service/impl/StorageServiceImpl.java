package com.koala.wms.service.impl;

import com.koala.wms.entity.Storage;
import com.koala.wms.mapper.StorageMapper;
import com.koala.wms.service.IStorageService;
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
 * @since 2023-10-02
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public IPage<Storage> pageCustom(IPage<Storage> page, Wrapper<Storage> wrapper) {
        return storageMapper.pageCustom(page, wrapper);
    }

}
