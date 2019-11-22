package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.PoseStyleEntity;
import com.pengjunlee.domain.ThemeEntity;
import com.pengjunlee.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface PoseStyleService extends IService<PoseStyleEntity> {

    List<PoseStyleEntity> listByOrder();
}
