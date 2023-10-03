package com.koala.wms.service;

import com.koala.wms.entity.Record;
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
public interface IRecordService extends IService<Record> {
    IPage<Record> pageCustom(IPage<Record> page, Wrapper<Record> wrapper);
}
