package com.pengjunlee.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:30
 */
@Data
@TableName("tbl_device_pose")//@TableName中的值对应着表名
public class DevicePoseEntity extends BaseDomain {

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

    private String dayType;
    private Long poseStyleId; // 主键ID

    private String poseType;

    private Long pose_id;
    private Long device_id;

    private String thumbnail; // 文件名

    private String diagram; // 文件路径


    private Integer topBottom;

    private Integer leftRight;
    private Integer upDown;
    private Integer rotate;


}
