package com.pengjunlee.controller;


import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.domain.ThemeEntity;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.ThemeService;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.EntityUtil;
import com.pengjunlee.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1/theme")
@RestController
@CrossOrigin
public class ThemeController extends BaseController {

    @Resource
    private ThemeService themeService;


    @PostMapping("/create")
    // @RequiresRoles(value = {"administrator"})
    public Object createTheme(@RequestBody ThemeEntity themeEntity) {
        boolean save = themeService.save(themeEntity);

        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (save) {
            ret.setCode(0);
            ret.setMessage("主题添加成功");
            ret.setData(themeEntity);
        } else {
            ret.setCode(-1);
            ret.setMessage("主题添加失败");
        }

        return ret;
    }

    @PutMapping("/update")
    public Object updateTheme(@RequestBody ThemeEntity themeEntity) {
        boolean b = false;
        if (themeEntity.getId() != null) {
            b = themeService.updateById(themeEntity);
        }
        return getUpdateResponse(b);
    }


    @GetMapping("/list")
    public Object themeList(@RequestParam Map<String, Object> params) {
        PageUtil pageUtil = themeService.pageThemeByCond(params);
        return getQueryResponse(pageUtil);

    }

    @DeleteMapping("/delete/{id}")
    @RequiresRoles(value = {"administrator"})
    public Object deleteTheme(@PathVariable(name = "id") Long id) {
        boolean b = themeService.removeById(id);
        return getDeleteResponse(b);

    }
}