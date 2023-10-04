package com.koala.wms.controller;


import java.time.LocalDateTime;
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
import com.koala.wms.entity.Goods;
import com.koala.wms.entity.Record;
import com.koala.wms.service.IGoodsService;
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

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/pageCustom")
    public Result pageCustom(@RequestBody QueryPageParam query) {
        HashMap<String, Object> param = query.getParam();
        String name = (String) param.get("name");
        String goodstype = (String) param.get("goodstype");
        String storage = (String) param.get("storage");
        String roleId = (String) param.get("roleId");
        String userId = (String) param.get("userId");

        Page<Record> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("r.goods=g.id and g.storage=s.id and g.goods_type=t.id");

        if ("2".equals(roleId)) {
            queryWrapper.apply("r.user_id=" + userId);
        }

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

    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoods());
        int n = record.getCount();
        //outbound
        if ("2".equals(record.getAction())) {
            n = -n;
            record.setCount(n);
        }
        int num = goods.getCount() + n;
        goods.setCount(num);
        goodsService.updateById(goods);
        record.setCreatetime(LocalDateTime.now());

        return recordService.save(record) ? Result.sucess() : Result.fail();
    }
}
