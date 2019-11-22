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
@TableName("tbl_global_set")//@TableName中的值对应着表名
public class GlobalSetEntity extends BaseDomain {

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

    private Long deviceId; // 设备ID

    private Float startPrice; // mac
    private Float photoPrice; // 合作商ID

    private Integer takeTime; // 合作商昵称

    private Integer choseTime; // 合作商分润比例

    private Integer payTime; // 场景类型

    private String waterFile; // 场景等级

    private String waterPath; // A 正常运行，其他为异常

    private Long themeId;

    private Integer takeRestTime;
    private Integer choseRestTime;
    private  Integer topBottom;

    private Integer leftRight;
    private Integer upDown;
    private Integer rotate;

    public void init(Long deviceId) {
        this.deviceId = deviceId;
        this.startPrice = 0.00F;
        this.photoPrice = 0.00F;
        this.takeTime = 0;
        this.choseTime = 0;
        this.payTime = 0;
        this.waterFile = "";
        this.waterPath = "";
        this.takeRestTime = 0;
        this.choseRestTime = 0;
        this.themeId = 0L;
        this.topBottom = 0;
        this.leftRight = 0;
        this.upDown = 0;
        this.rotate = 0;

    }

}
