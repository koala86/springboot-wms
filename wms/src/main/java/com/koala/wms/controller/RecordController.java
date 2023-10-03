package com.koala.wms.controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koala.wms.common.QueryPageParam;
import com.koala.wms.common.Result;
import com.koala.wms.entity.Record;
import com.koala.wms.service.IRecordService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koala
 * @since 2023-10-03
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private IRecordService recordService;

    @PostMapping("/pageCustom")
    public Result pageCustom(@RequestBody QueryPageParam query) {
        HashMap<String, Object> param = query.getParam();
        String name = (String) param.get("name");
        String goodstype = (String) param.get("goodstype");
        String storage = (String) param.get("storage");

        Page<Record> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("r.goods=g.id and g.storage=s.id and g.goods_type=t.id");
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            queryWrapper.like("g.name",name);
        }
        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
            queryWrapper.eq("t.id",goodstype);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
            queryWrapper.eq("s.id",storage);
        }

        IPage<Record> result = recordService.pageCustom(page, queryWrapper);
        System.out.println(result.getTotal());
        return Result.sucess(result.getRecords(), result.getTotal());
    }
}