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
import com.koala.wms.entity.User;
import com.koala.wms.service.IMenuService;
import com.koala.wms.service.IUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koala
 * @since 2023-08-25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/findByAccount")
    public Result findByAccount(@RequestParam String account) {
        List<User> list = userService.lambdaQuery().eq(User::getAccount,account).list();
        return list.size()>0 ? Result.sucess(list) : Result.fail();
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.sucess() : Result.fail();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return userService.removeById(id) ? Result.sucess() : Result.fail();
    }

    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getAccount,user.getAccount())
                            .eq(User::getPassword, user.getPassword()).list();
        if (list.size() >0) {
            User loginUser = list.get(0);
            List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright,loginUser.getRoleId()).list();
            Map<String, Object> resultMap = new HashMap<String,Object>();
            resultMap.put("user", loginUser);
            resultMap.put("menu", menuList);
            System.out.println(resultMap);
            return Result.sucess(resultMap);
        }
        return list.size() > 0 ? Result.sucess(list.get(0)) : Result.fail();
    }

    @GetMapping("/remove")
    public boolean remove(Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/pageCustom")
    public Result pageCustom(@RequestBody QueryPageParam query) {
        HashMap<String, Object> param = query.getParam();
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String roleId = (String) param.get("roleId");
        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(sex)) {
            lambdaQueryWrapper.eq(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId)) {
            lambdaQueryWrapper.eq(User::getRoleId, roleId);
        }

        IPage<User> result = userService.pageCustom(page, lambdaQueryWrapper);
        System.out.println(result.getTotal());
        return Result.sucess(result.getRecords(), result.getTotal());
    }
}
