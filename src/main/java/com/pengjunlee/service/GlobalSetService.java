package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.GlobalSetEntity;
import com.pengjunlee.utils.EntityUtil;
import com.pengjunlee.utils.PageUtil;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface GlobalSetService extends IService<GlobalSetEntity> {

    // T getById(Serializable id);

    // boolean updateById(T entity);

    // boolean save(T entity);

    EntityUtil<GlobalSetEntity> getByDeviceId(Long deviceId);

    boolean updateByDeviceId(GlobalSetEntity globalSetEntity);
}
