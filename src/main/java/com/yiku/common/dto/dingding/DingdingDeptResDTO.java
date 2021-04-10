package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/8 18:25
 */
@Data
public class DingdingDeptResDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;


    /**
     * 部门群已经创建后，有新人加入部门是否会自动加入该群：
     */

    private String auto_add_user;
    /**
     * 是否同步创建一个关联此部门的企业群：
     */

    private String create_dept_group;

    /**
     * 部门ID。
     */
    private String dept_id;

    /**
     * 部门名称。
     */
    private String name;

    /**
     * 父部门ID。
     */
    private String parent_id;

}
