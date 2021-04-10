package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/8 21:30
 */
@Data
public class DingdingDepartmentResDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 部门id
     */
    private String dept_id;

    /**
     * 部门名字
     */
    private String name;

}
