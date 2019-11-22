package com.pengjunlee.controller;


import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.domain.DeviceEntity;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.DeviceService;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


public class BaseController {
    

    protected Object getUpdateResponse(boolean b) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (b) {
            ret.setCode(0);
            ret.setMessage("数据更新成功");
        } else {
            ret.setCode(-1);
            ret.setMessage("更新数据失败");
        }
        return ret;
    }


    protected Object getQueryResponse(Object o) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (null != o) {
            ret.setCode(0);
            ret.setMessage("数据加载成功");
            ret.setData(o);
        } else {
            ret.setCode(-1);
            ret.setMessage("获取数据失败");
        }
        return ret;

    }

    protected Object getDeleteResponse(boolean b) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (b) {
            ret.setCode(0);
            ret.setMessage("数据删除成功");

        } else {
            ret.setCode(-1);
            ret.setMessage("删除数据失败");
        }
        return ret;

    }
}