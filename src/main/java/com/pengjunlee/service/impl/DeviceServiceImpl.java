package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.DeviceEntity;
import com.pengjunlee.domain.SearchEntity;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.DeviceService;
import com.pengjunlee.service.mapper.DeviceMapper;
import com.pengjunlee.service.mapper.UserMapper;
import com.pengjunlee.utils.EntityUtil;
import com.pengjunlee.utils.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:19
 */
@Service("deviceService")
@Transactional
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, DeviceEntity> implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public DeviceEntity getByMac(String mac) {
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DeviceEntity::getMac, mac);
        return getOne(queryWrapper);
    }

    @Override
    public EntityUtil<DeviceEntity> getDeviceBasicById(Long id) {
        DeviceEntity device = getById(id);
        List<SearchEntity> searchEntities = userMapper.listSearchEntity();
        if (null != device) {
            return new EntityUtil<>(device, searchEntities);
        }
        return null;
    }

    @Override
    public PageUtil pageDeviceByCond(Map<String, Object> map) {
        Integer offset = PageUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<DeviceEntity> devices = deviceMapper.pageDeviceByCond(map);
        int count = deviceMapper.countDeviceByCond(map);
        List<SearchEntity> searchEntities = userMapper.listSearchEntity();
        return new PageUtil(devices, count, searchEntities);
    }

}
