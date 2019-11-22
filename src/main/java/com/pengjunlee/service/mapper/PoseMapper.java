package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.PoseEntity;
import com.pengjunlee.domain.PoseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface PoseMapper extends BaseMapper<PoseEntity> {
    List<PoseEntity> pagePoseByCond(Map<String, Object> map);

    int countPoseByCond(Map<String, Object> map);

}
