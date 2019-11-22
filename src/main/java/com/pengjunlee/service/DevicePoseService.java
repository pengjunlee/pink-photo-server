package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.DevicePoseEntity;
import com.pengjunlee.domain.PoseEntity;
import com.pengjunlee.utils.PageUtil;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface DevicePoseService extends IService<DevicePoseEntity> {
    PageUtil pageDevicePoseByCond(Map<String, Object> map);
}
