package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.BodySetEntity;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface BodySetService extends IService<BodySetEntity> {

    // T getById(Serializable id);

    // boolean updateById(T entity);

    // boolean save(T entity);

    BodySetEntity findByParams(Map<String, Object> params);

    boolean updateByParams(BodySetEntity bodySetEntity);
}
