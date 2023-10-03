package com.koala.wms.service.impl;

import com.koala.wms.entity.Record;
import com.koala.wms.mapper.RecordMapper;
import com.koala.wms.service.IRecordService;
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
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public IPage<Record> pageCustom(IPage<Record> page, Wrapper<Record> wrapper) {
        return recordMapper.pageCustom(page, wrapper);
    }

}
