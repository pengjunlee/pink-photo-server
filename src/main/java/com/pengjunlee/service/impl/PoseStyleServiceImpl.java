package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.PoseStyleEntity;
import com.pengjunlee.service.PoseStyleService;
import com.pengjunlee.service.mapper.PoseStyleMapper;
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
@Service("poseStyleService")
@Transactional
public class PoseStyleServiceImpl extends ServiceImpl<PoseStyleMapper, PoseStyleEntity> implements PoseStyleService {

    @Resource
    private PoseStyleMapper poseStyleMapper;


    @Override
    public List<PoseStyleEntity> listByOrder() {
        return poseStyleMapper.listByOrder();
    }
}
