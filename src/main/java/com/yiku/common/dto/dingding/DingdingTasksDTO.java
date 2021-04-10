package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/31 11:10
 */
@Data
public class DingdingTasksDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 活动id
     */
    private String activity_id;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 任务结果
     */
    private String task_result;

    /**
     * 任务状态
     */
    private String task_status;

    /**
     * 任务id
     */
    private String taskid;

    /**
     * 地址
     */
    private String url;

    /**
     * 用户id
     */
    private String userid;
}
