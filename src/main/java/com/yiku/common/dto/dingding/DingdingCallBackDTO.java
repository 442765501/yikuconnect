package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/13 9:33
 */
@Data
public class DingdingCallBackDTO implements Serializable {


    private static final long serialVersionUID = 4088641604395547450L;


    /**
     * 组织架构id
     */
    private String CorpId;

    /**
     * 业务id
     */
    private String processInstanceId;

    /**
     * 事件
     */
    private String EventType;

    /**
     * 标签id集合
     */
    private String LabelIdList;

    /**
     * 触发活动
     */
    private String action;

    /**
     * 时间戳
     */
    private String TimeStamp;

    /**
     * 类型
     */
    private String type;

}
