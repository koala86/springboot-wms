package com.koala.wms.controller;


import java.util.HashMap;
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
import com.koala.wms.entity.Goods;
import com.koala.wms.service.IGoodsService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koala
 * @since 2023-10-03
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        return goodsService.save(goods) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        return goodsService.updateById(goods) ? Result.sucess() : Result.fail();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return goodsService.removeById(id) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/pageCustom")
    public Result pageCustom(@RequestBody QueryPageParam query) {
        HashMap<String, Object> param = query.getParam();
        String name = (String) param.get("name");
        String goodstype = (String) param.get("goodstype");
        String storage = (String) param.get("storage");

        Page<Goods> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }
        if (StringUtils.isNotBlank(goodstype)) {
            lambdaQueryWrapper.eq(Goods::getGoodsType, goodstype);
        }
        if (StringUtils.isNotBlank(storage)) {
            lambdaQueryWrapper.eq(Goods::getStorage, storage);
        }

        IPage<Goods> result = goodsService.pageCustom(page, lambdaQueryWrapper);
        System.out.println(result.getTotal());
        return Result.sucess(result.getRecords(), result.getTotal());
    }
}
