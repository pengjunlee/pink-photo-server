package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.BodySetEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface BodySetMapper extends BaseMapper<BodySetEntity> {

    BodySetEntity findByParams(BodySetEntity bodySetEntity);

    boolean updateByParams(BodySetEntity bodySetEntity);

}
