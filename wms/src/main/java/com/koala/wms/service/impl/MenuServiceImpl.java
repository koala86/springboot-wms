package com.koala.wms.service.impl;

import com.koala.wms.entity.Menu;
import com.koala.wms.mapper.MenuMapper;
import com.koala.wms.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
