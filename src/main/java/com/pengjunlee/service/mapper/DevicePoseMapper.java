package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.DevicePoseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface DevicePoseMapper extends BaseMapper<DevicePoseEntity> {
    List<DevicePoseEntity> pageDevicePoseByCond(Map<String, Object> map);

    int countDevicePoseByCond(Map<String, Object> map);

}
