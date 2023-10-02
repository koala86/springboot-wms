package com.koala.wms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.koala.wms.entity.Menu;
import com.koala.wms.entity.Storage;
import com.koala.wms.service.IStorageService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koala
 * @since 2023-10-02
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private IStorageService storageService;

    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.sucess() : Result.fail();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return storageService.removeById(id) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/pageCustom")
    public Result pageCustom(@RequestBody QueryPageParam query) {
        HashMap<String, Object> param = query.getParam();
        String name = (String) param.get("name");

        Page<Storage> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Storage> lambdaQueryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }

        IPage<Storage> result = storageService.pageCustom(page, lambdaQueryWrapper);
        System.out.println(result.getTotal());
        return Result.sucess(result.getRecords(), result.getTotal());
    }
}
