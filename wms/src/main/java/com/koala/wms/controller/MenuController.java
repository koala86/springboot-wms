package com.koala.wms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koala.wms.common.Result;
import com.koala.wms.entity.Menu;
import com.koala.wms.service.IMenuService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koala
 * @since 2023-10-02
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        List<Menu> list = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        return Result.sucess(list);
    }
}
