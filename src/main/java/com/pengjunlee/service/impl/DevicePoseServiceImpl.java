package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.DevicePoseEntity;
import com.pengjunlee.domain.PoseEntity;
import com.pengjunlee.service.DevicePoseService;
import com.pengjunlee.service.PoseService;
import com.pengjunlee.service.mapper.DevicePoseMapper;
import com.pengjunlee.service.mapper.PoseMapper;
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
@Service("devicePoseService")
@Transactional
public class DevicePoseServiceImpl extends ServiceImpl<DevicePoseMapper, DevicePoseEntity> implements DevicePoseService {

    @Resource
    private DevicePoseMapper devicePoseMapper;


    @Override
    public PageUtil pageDevicePoseByCond(Map<String, Object> map) {
        Integer offset = PageUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<DevicePoseEntity> poses = devicePoseMapper.pageDevicePoseByCond(map);
        int count = devicePoseMapper.countDevicePoseByCond(map);
        return new PageUtil(poses, count);
    }
}
