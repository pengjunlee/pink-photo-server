package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.DeviceEntity;
import com.pengjunlee.utils.EntityUtil;
import com.pengjunlee.utils.PageUtil;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface DeviceService extends IService<DeviceEntity> {

    // T getById(Serializable id);

    // boolean updateById(T entity);

    // boolean save(T entity);

    DeviceEntity getByMac(String mac);

    EntityUtil<DeviceEntity> getDeviceBasicById(Long id);

    PageUtil pageDeviceByCond(Map<String, Object> map);
}
