package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.BodySetEntity;
import com.pengjunlee.domain.GlobalSetEntity;
import com.pengjunlee.domain.SearchEntity;
import com.pengjunlee.service.BodySetService;
import com.pengjunlee.service.mapper.BodySetMapper;
import com.pengjunlee.service.mapper.BodySetMapper;
import com.pengjunlee.utils.EntityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:19
 */
@Service("bodySetService")
@Transactional
public class BodySetServiceImpl extends ServiceImpl<BodySetMapper, BodySetEntity> implements BodySetService {

    @Resource
    private BodySetMapper bodySetMapper;


    @Override
    public BodySetEntity findByParams(Map<String, Object> params) {

        if (StringUtils.isNotBlank(params.get("deviceId").toString()) && StringUtils.isNotBlank(params.get("poseType").toString()) && StringUtils.isNotBlank(params.get("poseStyleId").toString())) {
            QueryWrapper<BodySetEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(BodySetEntity::getDeviceId, Long.parseLong(params.get("deviceId").toString()));
            queryWrapper.and(wrapper -> wrapper.eq("pose_type", params.get("poseType").toString()));
            queryWrapper.and(wrapper -> wrapper.eq("pose_style_id", params.get("poseStyleId").toString()));
            BodySetEntity dbBody = getOne(queryWrapper);
            if (null != dbBody) {
                return dbBody;
            } else {
                BodySetEntity bodySetEntity = new BodySetEntity();
                bodySetEntity.setDeviceId(Long.parseLong(params.get("deviceId").toString()));
                bodySetEntity.setPoseType(params.get("poseType").toString());
                bodySetEntity.setPoseStyleId(Long.parseLong(params.get("poseStyleId").toString()));
                bodySetEntity.init();
                save(bodySetEntity);
                return bodySetEntity;
            }
        }
        return null;
    }

    @Override
    public boolean updateByParams(BodySetEntity bodySetEntity) {
        UpdateWrapper<BodySetEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(BodySetEntity::getDeviceId, bodySetEntity.getDeviceId());
        updateWrapper.and(wrapper -> wrapper.eq("pose_type", bodySetEntity.getPoseType()));
        updateWrapper.and(wrapper -> wrapper.eq("pose_style_id", bodySetEntity.getPoseStyleId()));
        return update(bodySetEntity, updateWrapper);
    }
}
