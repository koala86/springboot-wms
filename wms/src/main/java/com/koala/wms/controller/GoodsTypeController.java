package com.koala.wms.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koala.wms.common.QueryPageParam;
import com.koala.wms.common.Result;
import com.koala.wms.entity.GoodsType;
import com.koala.wms.service.IGoodsTypeService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koala
 * @since 2023-10-03
 */
@RestController
@RequestMapping("/goods-type")
public class GoodsTypeController {

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @PostMapping("/save")
    public Result save(@RequestBody GoodsType goodType) {
        return goodsTypeService.save(goodType) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody GoodsType goodType) {
        return goodsTypeService.updateById(goodType) ? Result.sucess() : Result.fail();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return goodsTypeService.removeById(id) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/pageCustom")
    public Result pageCustom(@RequestBody QueryPageParam query) {
        HashMap<String, Object> param = query.getParam();
        String name = (String) param.get("name");

        Page<GoodsType> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<GoodsType> lambdaQueryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(GoodsType::getName, name);
        }

        IPage<GoodsType> result = goodsTypeService.pageCustom(page, lambdaQueryWrapper);
        System.out.println(result.getTotal());
        return Result.sucess(result.getRecords(), result.getTotal());
    }

    @GetMapping("/list")
    public Result list() {
        List<GoodsType> list = goodsTypeService.list();
        return Result.sucess(list);
    }

}
