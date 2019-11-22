package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.SearchEntity;
import com.pengjunlee.domain.ThemeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface ThemeMapper extends BaseMapper<ThemeEntity> {


    List<ThemeEntity> pageThemeByCond(Map<String, Object> map);

    int countThemeByCond(Map<String, Object> map);

    List<SearchEntity> listSearchEntity();

}
