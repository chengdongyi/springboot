package com.example.domain.lvip;

import lombok.Data;

import java.util.Date;

/**
 * @author chengdongyi
 * @description:
 * @date 2019/12/16 14:36
 */
@Data
public class LvipProperty {

    /**
     * 配置项名
     */
    private String proName;

    /**
     * 配置项值
     */
    private String proValue;

    /**
     * 有效开始时间
     */
    private Date startTime;

    /**
     * 有效结束时间
     */
    private Date endTime;

    /**
     * 配置项说明
     */
    private String proComment;

    /**
     * 配置项分类
     */
    private String proType;

}
