package com.pengjunlee.controller;


import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.domain.UserAuthInfo;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.FdfsUtil;
import com.pengjunlee.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1/upload")
@RestController
@CrossOrigin
public class UploadController {


    @PostMapping("/file")
    public Object uploadFile(@RequestParam(required = false, value = "file") MultipartFile file, @RequestParam(value = "data", required = false) Object params) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        try {
            String filePath = FdfsUtil.upload(file.getInputStream(), file.getName(), file.getSize());
            ret.setCode(0);

            ret.setMessage("文件上传成功");

            Map<String, Object> map = new HashMap<>();
            map.put("filePath", filePath);
            if (null != params) {
                map.put("params", params);
            }
            ret.setData(map);
        } catch (Exception e) {
            ret.setCode(-1);
            ret.setMessage("文件上传失败");
        }

        return ret;

    }
}