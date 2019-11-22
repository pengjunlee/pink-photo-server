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
@TableName("tbl_device")//@TableName中的值对应着表名
public class DeviceEntity extends BaseDomain {

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

    private String name; // 设备名

    private String mac; // mac
    private Long userId; // 合作商ID

    private String userName; // 合作商昵称

    private Float royaltyRatio; // 合作商分润比例

    private String sceneType; // 场景类型

    private String sceneLevel; // 场景等级

    private String runStatus; // A 正常运行，其他为异常

    private String location; // 位置


}
