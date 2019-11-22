package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.DeviceEntity;
import com.pengjunlee.domain.SearchEntity;
import com.pengjunlee.domain.UserAuthInfo;
import com.pengjunlee.domain.GlobalSetEntity;
import com.pengjunlee.service.GlobalSetService;
import com.pengjunlee.service.mapper.GlobalSetMapper;
import com.pengjunlee.service.mapper.ThemeMapper;
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
@Service("globalSetService")
@Transactional
public class GlobalSetServiceImpl extends ServiceImpl<GlobalSetMapper, GlobalSetEntity> implements GlobalSetService {

    @Resource
    private ThemeMapper themeMapper;

    @Override
    public EntityUtil<GlobalSetEntity> getByDeviceId(Long deviceId) {
        QueryWrapper<GlobalSetEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GlobalSetEntity::getDeviceId, deviceId);
        GlobalSetEntity globalSetEntity = getOne(queryWrapper);
        List<SearchEntity> searchEntities = themeMapper.listSearchEntity();

        if (null == globalSetEntity) {
            globalSetEntity = new GlobalSetEntity();
            globalSetEntity.init(deviceId);
            save(globalSetEntity);
        }
        return new EntityUtil<>(globalSetEntity, searchEntities);
    }

    @Override
    public boolean updateByDeviceId(GlobalSetEntity globalSetEntity) {
        UpdateWrapper<GlobalSetEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(GlobalSetEntity::getDeviceId, globalSetEntity.getDeviceId());
        return update(globalSetEntity, updateWrapper);
    }
}
