package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.ThemeEntity;
import com.pengjunlee.utils.EntityUtil;
import com.pengjunlee.utils.PageUtil;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface ThemeService extends IService<ThemeEntity> {

    // T getById(Serializable id);

    // boolean updateById(T entity);

    // boolean save(T entity);

    PageUtil pageThemeByCond(Map<String, Object> map);
}
