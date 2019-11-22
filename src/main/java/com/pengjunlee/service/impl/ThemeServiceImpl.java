package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.ThemeEntity;
import com.pengjunlee.service.ThemeService;
import com.pengjunlee.service.mapper.ThemeMapper;
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
@Service("themeService")
@Transactional
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, ThemeEntity> implements ThemeService {

    @Resource
    private ThemeMapper themeMapper;

    @Override
    public PageUtil pageThemeByCond(Map<String, Object> map) {
        Integer offset = PageUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<ThemeEntity> themes = themeMapper.pageThemeByCond(map);
        int count = themeMapper.countThemeByCond(map);
        return new PageUtil(themes, count);
    }

}
