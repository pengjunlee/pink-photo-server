package com.pengjunlee.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:30
 */
@Data
@TableName("tbl_sys_user")//@TableName中的值对应着表名
public class UserEntity extends BaseDomain {

    /**
     * 主键
     *
     * @TableId中可以决定主键的类型,不写会采取默认值,默认值可以在yml中配置 AUTO: 数据库ID自增
     * INPUT: 用户输入ID
     * ID_WORKER: 全局唯一ID，Long类型的主键
     * ID_WORKER_STR: 字符串全局唯一ID
     * UUID: 全局唯一ID，UUID类型的主键
     * NONE: 该类型为未设置主键类型
     */
    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID

    private String name; // 登录用户名

    private String password; // 登录密码

    private String nickName; // 昵称

    private String introduction; // 简介

    private String roles; // 角色

    private String avatar; // 头像

    private Boolean locked; // 0 未锁定，1 锁定

    private Boolean imageBrowse; // 0 无权限，1 有权限

    private Float royaltyRatio;

    private String contactName;

    private String contactPhone;

    private String province;

    private String city;

    private String address;

}
