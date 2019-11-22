package com.pengjunlee.controller;


import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.domain.PoseEntity;
import com.pengjunlee.domain.PoseStyleEntity;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.PoseService;
import com.pengjunlee.service.PoseStyleService;
import com.pengjunlee.utils.FdfsUtil;
import com.pengjunlee.utils.PageUtil;
import com.pengjunlee.utils.ZipUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1/pose")
@RestController
@CrossOrigin
public class PoseController extends BaseController {

    @Resource
    private PoseStyleService poseStyleService;
    @Resource
    private PoseService poseService;

    @GetMapping("/style/list")
    public Object poseStyleList() {

        return poseStyleService.listByOrder();

    }

    @GetMapping("/list")
    public Object poseList(@RequestParam Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        List<PoseStyleEntity> poseStyleEntities = poseStyleService.listByOrder();
        map.put("styles", poseStyleEntities);
        PageUtil pageUtil = poseService.pagePoseByCond(params);

        map.put("poses", pageUtil);
        return getQueryResponse(map);

    }

    @PutMapping("/style/update")
    public Object poseUpdate(@RequestBody List<PoseStyleEntity> styles) {
        for (PoseStyleEntity style : styles) {
            poseStyleService.updateById(style);
        }
        return getQueryResponse(styles);

    }


    @PostMapping("/batch/add")
    public Object batchAdd(@RequestBody PoseEntity poseEntity) {
        BaseResponse<Object> ret = new BaseResponse<Object>();

        if (poseEntity.getFilePath() != null && poseEntity.getDayType() != null && poseEntity.getPoseStyleId() != null && poseEntity.getPoseType() != null) {
            Date now = new Date();
            poseEntity.setCreatedTime(now);
            try {
                FdfsUtil.download(new FileOutputStream(new File("D:\\b.zip")), poseEntity.getFilePath());
                ZipUtil.delete("D:\\edas");
                ZipUtil.unzip("D:\\b.zip", "D:\\edas", "GBK");
                File poseFile = new File("D:\\edas\\pose");
                if (poseFile.exists()) {
                    File[] files = poseFile.listFiles();
                    for (File temp : files) {
                        if (temp.isDirectory()) {
                            File[] files1 = temp.listFiles();
                            if (files1.length == 2) {
                                String thumb = null;
                                String dia = null;
                                for (File temp1 : files1) {
                                    if ("a.jpg".equals(temp1.getName()) || "a.png".equals(temp1.getName())) {
                                        thumb = FdfsUtil.upload(temp1);
                                    }
                                    if ("b.jpg".equals(temp1.getName()) || "b.png".equals(temp1.getName())) {
                                        dia = FdfsUtil.upload(temp1);
                                    }
                                }
                                if (thumb != null && dia != null) {
                                    poseEntity.setThumbnail(thumb);
                                    poseEntity.setDiagram(dia);
                                    poseService.save(poseEntity);
                                }
                            }
                        }
                    }
                }
                ret.setCode(0);
                ret.setMessage("数据添加成功");
            } catch (Exception e) {
                ret.setCode(-1);
                ret.setMessage(e.getMessage());
            }
        } else {
            ret.setCode(-1);
            ret.setMessage("数据添加失败");
        }
        return ret;
    }

    @DeleteMapping("/delete/{id}")
    @RequiresRoles(value = {"administrator"})
    public Object poseList(@PathVariable(name = "id") Long id) {
        boolean b = poseService.removeById(id);
        return getDeleteResponse(b);

    }

    @PutMapping("/update")
    public Object updateUser(@RequestBody PoseEntity poseEntity) {
        boolean b = false;
        if (poseEntity.getId() != null) {
            b = poseService.updateById(poseEntity);
        }
        return getUpdateResponse(b);
    }
}