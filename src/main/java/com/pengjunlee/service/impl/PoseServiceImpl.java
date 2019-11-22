package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.PoseEntity;
import com.pengjunlee.domain.SearchEntity;
import com.pengjunlee.domain.PoseEntity;
import com.pengjunlee.service.PoseService;
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
@Service("poseService")
@Transactional
public class PoseServiceImpl extends ServiceImpl<PoseMapper, PoseEntity> implements PoseService {

    @Resource
    private PoseMapper poseMapper;


    @Override
    public PageUtil pagePoseByCond(Map<String, Object> map) {
        Integer offset = PageUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<PoseEntity> poses = poseMapper.pagePoseByCond(map);
        int count = poseMapper.countPoseByCond(map);
        return new PageUtil(poses, count);
    }
}
