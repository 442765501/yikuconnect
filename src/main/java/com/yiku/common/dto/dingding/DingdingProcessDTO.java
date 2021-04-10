package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/31 10:59
 */
@Data
public class DingdingProcessDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 商业行动
     */
    private String biz_action;

    /**
     * 业务id
     */
    private String business_id;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 表单值
     */
    private List<DingdingFormDTO> form_component_values;

    /**
     * 操作记录
     */
    private List<DingdingOperationDTO> operation_records;

    /**
     * 发起人部门Id
     */
    private String originator_dept_id;

    /**
     * 发起人部门名字
     */
    private String originator_dept_name;

    /**
     * 发起人部门id
     */
    private String originator_userid;

    /**
     * 结果
     */
    private String result;

    /**
     * 状态
     */
    private String status;

    /**
     * 任务
     */
    private List<DingdingTasksDTO> tasks;

    /**
     * 标题
     */
    private String title;


}
