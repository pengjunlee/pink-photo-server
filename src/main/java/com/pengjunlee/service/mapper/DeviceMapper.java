package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.DeviceEntity;
import com.pengjunlee.domain.SearchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DeviceEntity> {


    List<DeviceEntity> pageDeviceByCond(Map<String, Object> map);

    int countDeviceByCond(Map<String, Object> map);

}
