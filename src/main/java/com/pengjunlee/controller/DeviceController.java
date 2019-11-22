package com.pengjunlee.controller;


import com.pengjunlee.domain.*;
import com.pengjunlee.service.*;
import com.pengjunlee.utils.EntityUtil;
import com.pengjunlee.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1/device")
@RestController
@CrossOrigin
public class DeviceController extends BaseController {

    @Resource
    private DeviceService deviceService;


    @Resource
    private DevicePoseService devicePoseService;

    @Resource
    private UserService userService;


    @Resource
    private GlobalSetService globalSetService;


    @Resource
    private BodySetService bodySetService;


    @Resource
    private PoseStyleService poseStyleService;

    @GetMapping("/info")
    public Object getDeviceInfo(@RequestParam(name = "mac") String mac) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        DeviceEntity device = deviceService.getByMac(mac);
        ret.setCode(0);
        ret.setMessage("获取数据成功");
        if (device != null) {
            ret.setData(device);
        }
        return ret;
    }

    @PostMapping("/create")
    // @RequiresRoles(value = {"administrator"})
    public Object createDevice(@RequestBody DeviceEntity deviceEntity) {
        if (deviceEntity.getUserId() != null) {
            UserEntity user = userService.getById(deviceEntity.getUserId());
            if (user != null) {
                deviceEntity.setRoyaltyRatio(user.getRoyaltyRatio());
                deviceEntity.setUserName(user.getNickName());
                deviceEntity.setLocation(user.getProvince() + user.getCity() + user.getAddress());
            }
        }
        boolean save = deviceService.save(deviceEntity);

        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (save) {
            ret.setCode(0);
            ret.setMessage("设备添加成功");
            ret.setData(deviceEntity);
        } else {
            ret.setCode(-1);
            ret.setMessage("设备添加失败");
        }

        return ret;
    }

    @PutMapping("/update")
    public Object updateDevice(@RequestBody DeviceEntity deviceEntity) {
        boolean b = false;
        if (deviceEntity.getId() != null) {
            if (deviceEntity.getUserId() != null) {
                UserEntity user = userService.getById(deviceEntity.getUserId());
                if (user != null) {
                    deviceEntity.setUserName(user.getNickName());
                }
            }
            b = deviceService.updateById(deviceEntity);
        }
        return getUpdateResponse(b);
    }

    @PutMapping("/global/update")
    public Object updateGlobal(@RequestBody GlobalSetEntity globalSetEntity) {
        boolean b = false;
        if (globalSetEntity.getDeviceId() != null) {
            b = globalSetService.updateByDeviceId(globalSetEntity);
        }
        return getUpdateResponse(b);
    }

    @PutMapping("/body/update")
    public Object updateBody(@RequestBody BodySetEntity bodySetEntity) {
        boolean b = false;
        if (bodySetEntity.getDeviceId() != null) {
            b = bodySetService.updateByParams(bodySetEntity);
        }
        return getUpdateResponse(b);
    }

    @GetMapping("/list")
    public Object deviceList(@RequestParam Map<String, Object> params) {
        PageUtil pageUtil = deviceService.pageDeviceByCond(params);
        return getQueryResponse(pageUtil);

    }

    @GetMapping("/pose/list")
    public Object devicePoseList(@RequestParam Map<String, Object> params) {
        PageUtil pageUtil = devicePoseService.pageDevicePoseByCond(params);
        return getQueryResponse(pageUtil);

    }

    @GetMapping("/basic/{id}")
    public Object deviceBasic(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new HashMap<>();
        List<PoseStyleEntity> poseStyleEntities = poseStyleService.listByOrder();
        map.put("styles", poseStyleEntities);

        EntityUtil<DeviceEntity> device = deviceService.getDeviceBasicById(id);
        map.put("device", device);
        return getQueryResponse(map);

    }

    @DeleteMapping("/delete/{id}")
    @RequiresRoles(value = {"administrator"})
    public Object deleteDevice(@PathVariable(name = "id") Long id) {
        boolean b = deviceService.removeById(id);
        return getDeleteResponse(b);

    }

    @GetMapping("/global/{deviceId}")
    public Object globalSet(@PathVariable(name = "deviceId") Long deviceId) {
        EntityUtil<GlobalSetEntity> ret = globalSetService.getByDeviceId(deviceId);
        return getQueryResponse(ret);

    }

    @GetMapping("/body")
    public Object bodySet(@RequestParam  Map<String, Object> params) {
        BodySetEntity body = bodySetService.findByParams(params);
        return getQueryResponse(body);
    }
}